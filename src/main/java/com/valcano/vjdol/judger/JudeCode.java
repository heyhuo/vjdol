package com.valcano.vjdol.judger;

import java.io.*;

import static java.lang.Thread.sleep;

public class JudeCode {
    //提交代码函数
    public void checkSubmitCode(String code, String inDataPath, String outDataPath, int topicId, String stuNo, int pointId) {
        PrintStream ps = null;
        File file = new File(outDataPath);
        try {
            ps = new PrintStream(new FileOutputStream(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //提交代码编译运行
        JudgerUtil judgerUtil = new JudgerUtil(code, inDataPath, topicId, stuNo, pointId);
        new Thread(judgerUtil).start();
        //将运行结果写入文件
        System.setOut(ps);
        ps.flush();
        try {
            sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ps.close();
    }
}
