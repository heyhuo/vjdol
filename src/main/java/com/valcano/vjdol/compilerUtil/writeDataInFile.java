package com.valcano.vjdol.compilerUtil;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class writeDataInFile {
    public static void main(String[] args) throws IOException, IOException {
        String sContent = "1dsa 2";
        String sDestFile = "/Users/huoshan/IdeaProjects/vjdol/src/main/resources/testPointData/in/2.in";
        File destFile = new File(sDestFile);
        if (!destFile.exists())
            destFile.createNewFile();
        FileWriter fw = null;
        try {
            fw = new FileWriter(sDestFile);
            fw.write(sContent);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (fw != null) {
                fw.close();
                fw = null;
            }
        }
    }
}
