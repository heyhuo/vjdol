package com.valcano.vjdol.judger;

import java.io.*;

import static java.lang.Thread.sleep;

public class FileUtil {
    /**
     * 读取文件
     *
     * @param fileName
     * @return
     */
    public String readFile(String fileName) {
        //读取文件
        BufferedReader br = null;
        StringBuffer sb = null;
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "UTF-8")); //这里可以控制编码
            sb = new StringBuffer();
            String line = null;
            while ((line = br.readLine()) != null) {
                sb.append(line);
                sb.append("\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
//        File file = new File(fileName);
//        file.delete();
        String data = new String(sb); //StringBuffer ==> String
        System.out.println("数据为==> " + data);
        return data;
    }

    /**
     * 将数据写入文件
     *
     * @throws IOException
     */
    public void writeByFileWrite(String sContent, String sDestFile) {

        File destFile = new File(sDestFile);
        if (!destFile.exists()) {
            try {
                destFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        FileWriter fw = null;
        try {
            fw = new FileWriter(sDestFile);
            fw.write(sContent);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (fw != null) {
                try {
                    fw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                fw = null;
            }
        }
    }

}
