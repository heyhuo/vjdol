package com.valcano.vjdol.compilerUtil;

import java.io.*;
import java.lang.reflect.Method;

/**
 * Created by leeezm on 17-7-18.
 */
public class Main implements Runnable {

    @Override
    public void run() {
        try {
            FileInputStream fis = new FileInputStream("/Users/huoshan/IdeaProjects/vjdol/src/main/resources/testPointData/in/1_1.in");
            System.out.println(fis);
            //将1.in作为标准输入
            System.setIn(fis);
            Class clazz = null;
            //后面参数的类名必须为全限定类名(添加完整包名)
            try {
                clazz = Class.forName("/Users/huoshan/IdeaProjects/vjdol/src/main/resources/testPointData/stuCode/Main.class");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            //获取main方法
            Method mainMethod = clazz.getMethod("main", String[].class);
            //第一个参数是执行该方法的主调，后面是执行该方法时传入的实参
            mainMethod.invoke(null, (Object) new String[]{""});

            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws FileNotFoundException {

        Main main = new Main();
        new Thread(main).start();

        PrintStream ps = null;     // 声明打印流对象
        // 如果现在是使用FileOuputStream实例化，意味着所有的输出是向文件之中
        ps = new PrintStream(new FileOutputStream(new File("/Users/huoshan/IdeaProjects/vjdol/src/main/resources/testPointData/4.txt")));
        System.setOut(ps);
    }
}