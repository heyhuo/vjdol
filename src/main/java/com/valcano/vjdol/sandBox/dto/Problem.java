package com.valcano.vjdol.sandBox.dto;

import java.util.ArrayList;
import java.util.List;

public class Problem {
    private long timeLimit;
    private long memoryLimit;
    private String code;
    private int runId;
    private int pointNum;

    private String stuNo;

    public String getStuNo() {
        return stuNo;
    }

    public void setStuNo(String stuNo) {
        this.stuNo = stuNo;
    }

    public int getPointNum() {
        return pointNum;
    }

    public void setPointNum(int pointNum) {
        this.pointNum = pointNum;
    }

    @Override
    public String toString() {
        return "Problem{" +
                "timeLimit=" + timeLimit +
                ", memoryLimit=" + memoryLimit +
                ", code='" + code + '\'' +
                ", runId=" + runId +
                ", pointNum=" + pointNum +
                ", stuNo='" + stuNo + '\'' +
                ", inputDataFilePathList=" + inputDataFilePathList +
                '}';
    }

    public Problem(long timeLimit, long memoryLimit, String code, int runId, int pointNum, String stuNo, List<String> inputDataFilePathList) {
        this.timeLimit = timeLimit;
        this.memoryLimit = memoryLimit;
        this.code = code;
        this.runId = runId;
        this.pointNum = pointNum;
        this.stuNo = stuNo;
        this.inputDataFilePathList = inputDataFilePathList;
    }

    private List<String> inputDataFilePathList = new ArrayList<String>();

    public long getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(long timeLimit) {
        this.timeLimit = timeLimit;
    }

    public long getMemoryLimit() {
        return memoryLimit;
    }

    public void setMemoryLimit(long memoryLimit) {
        this.memoryLimit = memoryLimit;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getRunId() {
        return runId;
    }

    public void setRunId(int runId) {
        this.runId = runId;
    }

    public List<String> getInputDataFilePathList() {
        return inputDataFilePathList;
    }

    public void setInputDataFilePathList(List<String> inputDataFilePathList) {
        this.inputDataFilePathList = inputDataFilePathList;
    }

    public Problem() {
    }



}
