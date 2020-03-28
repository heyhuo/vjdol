package com.valcano.vjdol.judger;

import com.valcano.vjdol.dao.ExamPaperDao;
import com.valcano.vjdol.sandBox.SandboxSecurityManager;

import javax.tools.*;
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


public class JudgerUtil implements Runnable {
    private static Map<String, JavaFileObject> fileObjects = new ConcurrentHashMap<>();
    private String code;
    private String inDataPath;
    private int topicId;
    private String stuNo;
    private int pointId;
    private ExamPaperDao examPaperDao = new ExamPaperDao();
    private long startTime = 0;

    //构造函数
    public JudgerUtil(String code, String inDataPath, int topicId, String stuNo, int pointId) {
        this.code = code;
        this.inDataPath = inDataPath;
        this.topicId = topicId;
        this.stuNo = stuNo;
        this.pointId = pointId;

    }

    @Override
    public void run() {
        //设置权限
//        System.setSecurityManager(new SandboxSecurityManager());
        //获取开始时间
        startTime = System.currentTimeMillis();
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
            System.out.println("compile error");
            //获取结束时间
            long endTime = System.currentTimeMillis();
            long time = endTime - startTime;
            //将程序运行时间插入数据库
            examPaperDao.updateExamPaperTime(stuNo, topicId, pointId, time);
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
        if (result == true) {
            //ClassLoader: 根据字节码，生成类模板
            ClassLoader classloader = new MyClassLoader();

            Class<?> clazz = null;
            try {
                //加载类
                clazz = classloader.loadClass(clssName);
            } catch (ClassNotFoundException e) {
                System.out.println("compile error");
                long endTime = System.currentTimeMillis();
                long time = endTime - startTime;
                //将程序运行时间插入数据库
                examPaperDao.updateExamPaperTime(stuNo, topicId, pointId, time);

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
                System.out.println("segment error");
                long endTime = System.currentTimeMillis();
                long time = endTime - startTime;
                //将程序运行时间插入数据库
                examPaperDao.updateExamPaperTime(stuNo, topicId, pointId, time);
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            try {
                //实例化调用类中方法
                Object object = clazz.newInstance();

                method.invoke(object, (Object) new String[]{});

                //获取结束时间
                long endTime = System.currentTimeMillis();
                long time = endTime - startTime;
                //将程序运行时间插入数据库
                examPaperDao.updateExamPaperTime(stuNo, topicId, pointId, time);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("compile error");
            //获取结束时间
            long endTime = System.currentTimeMillis();
            long time = endTime - startTime;
            //将程序运行时间插入数据库
            examPaperDao.updateExamPaperTime(stuNo, topicId, pointId, time);
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
