package com.valcano.vjdol.sandBox.systemStream;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ThreadInputStream extends InputStream {
    /**
     * ThreadLocal为变量在每个线程中都创建了一个副本
     * 那么每个线程可以访问自己内部的副本变量
     */
    private volatile ThreadLocal<InputStream> localIn = new ThreadLocal<InputStream>();

    @Override
    public int read() throws IOException {
        return localIn.get().read();
    }

    public void setThreadIn(InputStream in) {

        //设置当前线程的线程局部变量的值
        localIn.set(in);
    }

    public void removeAndCloseThreadIn() {
        InputStream stream = localIn.get();
        localIn.remove();

        try {
            if (stream != null) {
                stream.close();
                stream = null;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
