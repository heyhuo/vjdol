package com.valcano.vjdol.compilerUtil;

import org.junit.Test;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.tools.DiagnosticCollector;
import javax.tools.FileObject;
import javax.tools.ForwardingJavaFileManager;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileManager;
import javax.tools.JavaFileObject;
import javax.tools.SimpleJavaFileObject;
import javax.tools.ToolProvider;

public class DynamicCompile implements Runnable {
    private static Map<String, JavaFileObject> fileObjects = new ConcurrentHashMap<>();
    private String code;
    private String inDataPath;

    public static void main(String[] args) throws FileNotFoundException {
        String a = "import java.util.Scanner;\n" +
                "\n" +
                "public class Main {\n" +
                "\n" +
                "\tpublic static void main(String[] args) {\n" +
                "\n" +
                "\t\tScanner in = new Scanner(System.in);\n" +
                "\t\tString[] sci = in.nextLine().split(\"[.E]\");\n" +
                "\t\tin.close();\n" +
                "\n" +
                "\t\tint eff = Integer.parseInt(sci[0]);\n" +
                "\t\tint pow = Integer.parseInt(sci[2]);\n" +
                "\t\tif (pow >= 0) {\n" +
                "\t\t\tSystem.out.print(eff);\n" +
                "\t\t\t\n" +
                "\t\t\tboolean havePoint = true;\n" +
                "\t\t\tif (sci[1].length() <= pow) {\n" +
                "\t\t\t\thavePoint = false;\n" +
                "\t\t\t}\n" +
                "\t\t\tint i = 0;\n" +
                "\t\t\tfor (i = 0; i < sci[1].length() && pow != 0; i++, pow--) {\n" +
                "\t\t\t\tSystem.out.print(sci[1].charAt(i));\n" +
                "\t\t\t}\n" +
                "\t\t\t\n" +
                "\t\t\tif (havePoint) {\n" +
                "\t\t\t\tSystem.out.print(\".\");\n" +
                "\t\t\t\tfor (; i < sci[1].length(); i++) {\n" +
                "\t\t\t\t\tSystem.out.print(sci[1].charAt(i));\n" +
                "\t\t\t\t}\n" +
                "\t\t\t}\n" +
                "\t\t\t\n" +
                "\t\t\twhile (pow != 0) {\n" +
                "\t\t\t\tSystem.out.print(\"0\");\n" +
                "\t\t\t\tpow--;\n" +
                "\t\t\t}\n" +
                "\t\t\t\n" +
                "\t\t} else {\n" +
                "\t\t\tif (eff < 0) {\n" +
                "\t\t\t\tSystem.out.print(\"-\");\n" +
                "\t\t\t\teff = -eff;\n" +
                "\t\t\t}\n" +
                "\t\t\t\n" +
                "\t\t\tSystem.out.print(\"0.\");\n" +
                "\t\t\tfor (int i = 1; i < Math.abs(pow); i++) {\n" +
                "\t\t\t\tSystem.out.print(\"0\");\n" +
                "\t\t\t}\n" +
                "\t\t\tSystem.out.print(eff);\n" +
                "\t\t\tSystem.out.print(sci[1]);\n" +
                "\t\t}\n" +
                "\t}\n" +
                "\n" +
                "}";
        String path = "/Users/huoshan/IdeaProjects/vjdol/src/main/resources/testPointData/1.in";
        DynamicCompile dynamicCompile = new DynamicCompile(a, path);
        new Thread(dynamicCompile).start();
        // 声明打印流对象
        PrintStream ps = null;
        String outDataFile = "4.txt";
        // 如果现在是使用FileOuputStream实例化，意味着所有的输出是向文件之中
        File file = new File("/Users/huoshan/IdeaProjects/vjdol/src/main/resources/testPointData" + File.separator + outDataFile);
        ps = new PrintStream(new FileOutputStream(file));
        System.setOut(ps);
        file.delete();
    }

    //构造函数

    public DynamicCompile(String code, String inDataPath) {
        this.code = code;
        this.inDataPath = inDataPath;
    }

    @Override
    public void run() {
        //查询数据库中代码

        dynamicExecute(code, inDataPath);
    }


    //动态编译执行函数
    public void dynamicExecute(String code, String in) {

        // 1、通过JavaCompiler动态编译
        //JavaCompiler: 负责读取源代码，编译诊断，输出class
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();

        DiagnosticCollector<JavaFileObject> collector = new DiagnosticCollector<>();

        //JavaFileManager: 管理JavaFileObject，负责JavaFileObject的创建和保存位置
        JavaFileManager javaFileManager = new MyJavaFileManager(compiler.getStandardFileManager(collector, null, null));


        //配置编译选项
        List<String> options = new ArrayList<>();
        options.add("-target");
        options.add("1.8");

        //编译模式的正则表达式
        /*
            [A-z] 等于 [a-zA-Z] 表示匹配所有大小写字母
            0-9 表示数字
            "-' 都是表示本义字符,匹配这三个符号
            \s 匹配非换行的所有空白字符
         */
        Pattern CLASS_PATTERN = Pattern.compile("class\\s+([$_a-zA-Z][$_a-zA-Z0-9]*)\\s*");
        //匹配代码
        Matcher matcher = CLASS_PATTERN.matcher(code);
        //代码类名
        String clssName;

        //正则匹配class 后的名称
        if (matcher.find()) {
            clssName = matcher.group(1);

        } else {
            throw new IllegalArgumentException("No such class name in " + code);
        }

        //JavaFileObject: 文件抽象，代表源代码或者编译后的class
        //将code编译成classname.java
        JavaFileObject javaFileObject = new MyJavaFileObject(clssName, code);

        //编译
        JavaCompiler.CompilationTask task = compiler
                .getTask(null, javaFileManager, collector, options, null, Arrays.asList(javaFileObject));
        //result: true：类编译成功，false：类编译失败
        Boolean result = task.call();


        //ClassLoader: 根据字节码，生成类模板
        ClassLoader classloader = new MyClassLoader();


        Class<?> clazz = null;
        try {
            //加载类
            clazz = classloader.loadClass(clssName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        //方法
        Method method = null;
        try {
            //
            FileInputStream fis = new FileInputStream(in);
            //将1.in作为标准输入
            System.setIn(fis);
            method = clazz.getMethod("main", String[].class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            //实例化调用类中方法
            Object object = clazz.newInstance();
            Object returnValue = method.invoke(object, (Object) new String[]{});
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
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
