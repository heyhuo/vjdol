package com.valcano.vjdol.sandBox.dto;

public class SandboxInitData {
    private int port;
    private String classFileRootPath;

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getClassFileRootPath() {
        return classFileRootPath;
    }

    public void setClassFileRootPath(String classFileRootPath) {
        this.classFileRootPath = classFileRootPath;
    }

    public SandboxInitData(int port, String classFileRootPath) {
        this.port = port;
        this.classFileRootPath = classFileRootPath;
    }

    public SandboxInitData(String classFileRootPath) {
        this.classFileRootPath = classFileRootPath;
    }
}
