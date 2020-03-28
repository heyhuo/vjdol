package com.valcano.vjdol.compilerUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@SuppressWarnings("unchecked")
public class TestThread {

    @SuppressWarnings("rawtypes")
    public static void main(String[] args) throws ExecutionException, InterruptedException, FileNotFoundException {
        PrintStream ps = null;     // 声明打印流对象
        // 如果现在是使用FileOuputStream实例化，意味着所有的输出是向文件之中
        ps = new PrintStream(new FileOutputStream(new File("/Users/huoshan/IdeaProjects/vjdol/src/main/resources/testPointData" + File.separator + "2.txt")));
        System.setOut(ps);

        Date date1 = new Date();

        int taskSize = 2;
        // 创建一个线程池
        ExecutorService pool = Executors.newFixedThreadPool(taskSize);

        String code = "\n" +
                "import java.util.Scanner;\n" +
                "\n" +
                "public class Test {\n" +
                "    public static void main(String[] args) {\n" +
                "        //提交的代码，循环进行输入\n" +
                "        Scanner scan = new Scanner(System.in);\n" +
                "        while (scan.hasNext()) {\n" +
                "            int a = scan.nextInt();\n" +
                "            int b = scan.nextInt();\n" +
                "            System.out.println(a + b);\n" +
                "        }\n" +
                "    }\n" +
                "}";

        String inDataPath = "/Users/huoshan/IdeaProjects/vjdol/src/main/resources/testPointData/";
        // 创建多个有返回值的任务
        List<Future> list = new ArrayList<Future>();
        for (int i = 1; i <= taskSize; i++) {
            System.out.println(inDataPath + i + ".in");
            Callable c = new DynamicTestCall(code, inDataPath + i + ".in");
            // 执行任务并获取Future对象
            Future f = pool.submit(c);
            list.add(f);
        }
        // 关闭线程池
        pool.shutdown();
//        System.out.println("----程序结束运行----，程序运行时间【" + (date2.getTime() - date1.getTime()) + "毫秒】");
    }

}
//
//class MyCallable implements Callable<Object> {
//    private String taskNum;
//
//    MyCallable(String taskNum) {
//        this.taskNum = taskNum;
//    }
//
//    @Override
//    public Object call() throws Exception {
//        System.out.println(">>>" + taskNum + "任务启动");
//        Date dateTmp1 = new Date();
//        Thread.sleep(1000);
//        Date dateTmp2 = new Date();
//        long time = dateTmp2.getTime() - dateTmp1.getTime();
//        System.out.println(">>>" + taskNum + "任务终止");
//        return taskNum + "任务返回运行结果,当前任务时间【" + time + "毫秒】";
//    }
//}
