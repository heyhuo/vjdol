package com.valcano.vjdol.compilerUtil;

import java.io.*;

public class ConsoleText {
//    static PipedInputStream pipedIS = new PipedInputStream();
//    static PipedOutputStream pipedOS =
//            new PipedOutputStream();
//
//    public static void main(String[] a) {
//        try {
//            pipedIS.connect(pipedOS);
//        } catch (IOException e) {
//            System.err.println("连接失败");
//            System.exit(1);
//        }
//        byte[] inArray = new byte[20];
//        byte[] outArray = new byte[20];
//        int bytesRead = 0;
//        try {
//            // 向pipedOS发送20字节数据
//            pipedOS.write(outArray, 0, 20);
//            System.out.println("     已发送20字节...");
//            // 在每一次循环迭代中，读入10字节
//            // 发送20字节
//            bytesRead = pipedIS.read(inArray, 0, 20);
//            System.out.println(bytesRead);
//            int i = 0;
//            while (bytesRead != -1) {
//                pipedOS.write(outArray, 0, 20);
//                System.out.println("     已发送20字节..." + i);
//                i++;
//                bytesRead = pipedIS.read(inArray, 0, 20);
//            }
//        } catch (IOException e) {
//            System.err.println("读取pipedIS时出现错误: " + e);
//            System.exit(1);
//        }
//    } // main()


    static PipedInputStream pipedIS =
            new PipedInputStream();
    static PipedOutputStream pipedOS =
            new PipedOutputStream();

    public static void main(String[] args) {
        try {
            pipedIS.connect(pipedOS);
        } catch (IOException e) {
            System.err.println("连接失败");
            System.exit(1);
        }
        byte[] inArray = new byte[10];
        int bytesRead = 0;
        // 启动写操作线程
        startWriterThread();
        try {
            bytesRead = pipedIS.read(inArray, 0, 10);
            while (bytesRead != -1) {
                System.out.println("已经读取" +
                        bytesRead + "字节...");
                bytesRead = pipedIS.read(inArray, 0, 10);
            }
        } catch (IOException e) {
            System.err.println("读取输入错误.");
            System.exit(1);
        }
    } // main()

    // 创建一个独立的线程
    // 执行写入PipedOutputStream的操作
    private static void startWriterThread() {
        new Thread(new Runnable() {
            public void run() {
                byte[] outArray = new byte[2000];
                while (true) { // 无终止条件的循环
                    try {
                        // 在该线程阻塞之前，有最多1024字节的数据被写入
                        pipedOS.write(outArray, 0, 2000);
                    } catch (IOException e) {
                        System.err.println("写操作错误");
                        System.exit(1);
                    }
                    System.out.println("     已经发送2000字节...");
                }
            }
        }).start();
    }
}