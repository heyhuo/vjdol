package com.valcano.vjdol.compilerUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

public class CompilerDemo {
    public static void main(String[] args) throws IOException {

        // 1、通过JavaCompiler动态编译
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
//        int result = compiler.run(null, null, null, "D:/Hello.java");
//        System.out.println(result == 0 ? "编译成功" : "编译失败");

        // 通过IO流操作，将字符串存储成一个临时文件，然后调用动态编译方法！
        String str = "public class demo {\n" +
                "    public static void main(String[] args) {\n" +
                "        System.out.println(\"HAHA\");\n" +
                "    }\n" +
                "}";

        // 2、通过Runtime.getRuntime()运行启动新的进程动态运行编译好的类
        Runtime run = Runtime.getRuntime();
        Process process = run.exec("java -cp d:/ Hello");
        InputStream in = process.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        String info = "";
        while ((info = reader.readLine()) != null) {
            System.out.println(info);
        }

        // 3、通过反射运行编译好的类
        try {
            URL[] urls = new URL[]{new URL("file:/" + "d:/")};
            URLClassLoader loader = new URLClassLoader(urls);
            Class c = loader.loadClass("Hello");
            // 调用加载类的main方法
            Method m = c.getMethod("main", String[].class);
            m.invoke(null, (Object) new String[]{});
            // m.invoke(null, new String[]{"a","b"});
            // 上面代码会被编译成：m.invoke(null,"a","b"),这样就发生参数个数不匹配的问题
            // 因此，必须加上(Object)进行转型，避免这个问题
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
