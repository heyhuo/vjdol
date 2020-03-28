package com.valcano.vjdol.entity;

public class TopicAnswer {
    //试卷号
    private int paper_id;

    //考生号
    private String stu_no;

    //题目号
    private int topic_id;

    //代码
    private String code;

    //题目分数
    private int topic_socre;

    //添加次数
    private int submit_num;

    public TopicAnswer() {

    }

    public int getSubmit_num() {
        return submit_num;
    }

    public void setSubmit_num(int submit_num) {
        this.submit_num = submit_num;
    }

    public int getPaper_id() {
        return paper_id;
    }

    public String getStu_no() {
        return stu_no;
    }

    public int getTopic_id() {
        return topic_id;
    }

    public String getCode() {
        return code;
    }

    public int getTopic_socre() {
        return topic_socre;
    }

    public void setPaper_id(int paper_id) {
        this.paper_id = paper_id;
    }

    public void setStu_no(String stu_no) {
        this.stu_no = stu_no;
    }

    public void setTopic_id(int topic_id) {
        this.topic_id = topic_id;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setTopic_socre(int topic_socre) {
        this.topic_socre = topic_socre;
    }

    @Override
    public String toString() {
        return "TopicAnswer{" +
                "paper_id=" + paper_id +
                ", stu_no='" + stu_no + '\'' +
                ", topic_id=" + topic_id +
                ", code='" + code + '\'' +
                ", topic_socre=" + topic_socre +
                '}';
    }
}
