package com.valcano.vjdol.sandBox.classLoader;

import javax.tools.*;
import java.io.*;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class SandboxClassLoader {
    private static Map<String, JavaFileObject> fileObjects = new ConcurrentHashMap<>();
    private String code;

    //构造函数
    public SandboxClassLoader(String code) {
        this.code = code;
    }

    //动态编译执行函数
    public Class<?> loadSandboxClass() {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        DiagnosticCollector<JavaFileObject> collector = new DiagnosticCollector<>();
        JavaFileManager javaFileManager = new MyJavaFileManager(compiler.getStandardFileManager(collector, null, null));
        List<String> options = new ArrayList<>();
        options.add("-target");
        options.add("1.8");
        Pattern CLASS_PATTERN = Pattern.compile("class\\s+([$_a-zA-Z][$_a-zA-Z0-9]*)\\s*");
        //匹配代码
        Matcher matcher = CLASS_PATTERN.matcher(code);
        //代码类名
        String clssName;

        //正则匹配class 后的名称
        if (matcher.find()) {
            clssName = matcher.group(1);

        } else {
            System.out.println("compile error");
            throw new IllegalArgumentException("No such class name in " + code);
        }
        JavaFileObject javaFileObject = new MyJavaFileObject(clssName, code);
        JavaCompiler.CompilationTask task = compiler
                .getTask(null, javaFileManager, collector, options, null, Arrays.asList(javaFileObject));
        Boolean result = task.call();
        if (result == true) {
            //ClassLoader: 根据字节码，生成类模板
            ClassLoader classloader = new MyClassLoader();
            Class<?> clazz = null;
            try {
                //加载类
                clazz = classloader.loadClass(clssName);
            } catch (ClassNotFoundException e) {
                System.out.println("compile error");
                e.printStackTrace();
            }
            return clazz;
        }else{
            System.out.println("编译错误");
        }
        return null;
    }

    /**
     * 类加载器
     */
    public static class MyClassLoader extends ClassLoader {

        @Override
        protected Class<?> findClass(String name) throws ClassNotFoundException {
            JavaFileObject fileObject = fileObjects.get(name);
            if (fileObject != null) {
                byte[] bytes = ((MyJavaFileObject) fileObject).getCompiledBytes();
                return defineClass(name, bytes, 0, bytes.length);
            }
            try {
                return ClassLoader.getSystemClassLoader().loadClass(name);
            } catch (Exception e) {
                return super.findClass(name);
            }
        }
    }

    public static class MyJavaFileObject extends SimpleJavaFileObject {
        private String source;
        private ByteArrayOutputStream outPutStream;

        public MyJavaFileObject(String name, String source) {
            super(URI.create("String:///" + name + Kind.SOURCE.extension), Kind.SOURCE);
            this.source = source;
        }

        public MyJavaFileObject(String name, Kind kind) {
            super(URI.create("String:///" + name + kind.extension), kind);
            source = null;

        }

        public CharSequence getCharContent(boolean ignoreEncodingErrors) {
            if (source == null) {
                throw new IllegalArgumentException("source == null");
            }
            return source;
        }

        public OutputStream openOutputStream() throws IOException {
            outPutStream = new ByteArrayOutputStream();
            return outPutStream;
        }

        public byte[] getCompiledBytes() {
            return outPutStream.toByteArray();
        }
    }

    public static class MyJavaFileManager
            extends ForwardingJavaFileManager<JavaFileManager> {
        protected MyJavaFileManager(JavaFileManager fileManager) {
            super(fileManager);

        }

        @Override
        public JavaFileObject getJavaFileForInput(Location location, String className, JavaFileObject.Kind kind)
                throws IOException {

            JavaFileObject javaFileObject = fileObjects.get(className);
            if (javaFileObject == null) {
                super.getJavaFileForInput(location, className, kind);
            }
            return javaFileObject;
        }

        @Override
        public JavaFileObject getJavaFileForOutput(Location location, String qualifiedClassName,
                                                   JavaFileObject.Kind kind, FileObject sibling) throws IOException {
            JavaFileObject javaFileObject = new MyJavaFileObject(qualifiedClassName, kind);
            fileObjects.put(qualifiedClassName, javaFileObject);
            return javaFileObject;
        }
    }
}
