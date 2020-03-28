package com.valcano.vjdol.entity;

//试卷表
public class ExamPaper {
    //试卷号
    private int paper_id;
    //考生号
    private String stu_no;
    //题号
    private Integer topic_id;
    //点号
    private Integer point_id;
    //运行时间
    private long time_limit;
    //内存限制
    private long memory_limit;
    //结果
    private String result;
    //评判结果
    private String judge_result;

    //测试点分数
    private int point_grade;

    public ExamPaper() {
    }

    public String getJudge_result() {
        return judge_result;
    }

    public void setJudge_result(String judge_result) {
        this.judge_result = judge_result;
    }

    public Integer getPoint_id() {
        return point_id;
    }

    public void setPoint_id(Integer point_id) {
        this.point_id = point_id;
    }

    public int getPaper_id() {
        return paper_id;
    }

    public String getStu_no() {
        return stu_no;
    }

    public Integer getTopic_id() {
        return topic_id;
    }

    public long getTime_limit() {
        return time_limit;
    }

    public long getMemory_limit() {
        return memory_limit;
    }

    public void setTime_limit(long time_limit) {
        this.time_limit = time_limit;
    }

    public void setMemory_limit(long memory_limit) {
        this.memory_limit = memory_limit;
    }

    public String getResult() {
        return result;
    }

    public int getPoint_grade() {
        return point_grade;
    }

    public void setPaper_id(int paper_id) {
        this.paper_id = paper_id;
    }

    public void setStu_no(String stu_no) {
        this.stu_no = stu_no;
    }

    public void setTopic_id(Integer topic_id) {
        this.topic_id = topic_id;
    }

    public void setTime_limit(int time_limit) {
        this.time_limit = time_limit;
    }

    public void setMemory_limit(int memory_limit) {
        this.memory_limit = memory_limit;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public void setPoint_grade(int point_grade) {
        this.point_grade = point_grade;
    }

    @Override
    public String toString() {
        return "ExamPaper{" +
                "paper_id=" + paper_id +
                ", stu_no='" + stu_no + '\'' +
                ", topic_id=" + topic_id +
                ", time_limit=" + time_limit +
                ", memory_limit=" + memory_limit +
                ", result='" + result + '\'' +
                ", point_grade=" + point_grade +
                '}';
    }

    public ExamPaper(String stu_no, Integer topic_id, Integer point_id, long time_limit, long memory_limit, String result) {
        this.stu_no = stu_no;
        this.topic_id = topic_id;
        this.point_id = point_id;
        this.time_limit = time_limit;
        this.memory_limit = memory_limit;
        this.result = result;
    }
}
