package com.valcano.vjdol.entity;

public class Student {
    //考号
    private String stu_no;

    //密码
    private String stu_pwd;

    //考生姓名
    private String stu_name;

    //身份证号
    private String stu_id;

    //试卷号
    private int paper_id;

    //总成绩
    private int total_grade;

    //证件照
    private String stu_pic;

    public Student() {
    }

    public String getStu_no() {
        return stu_no;
    }

    public String getStu_pwd() {
        return stu_pwd;
    }

    public String getStu_name() {
        return stu_name;
    }

    public String getStu_id() {
        return stu_id;
    }

    public int getPaper_id() {
        return paper_id;
    }

    public int getTotal_grade() {
        return total_grade;
    }

    public String getStu_pic() {
        return stu_pic;
    }

    public void setStu_no(String stu_no) {
        this.stu_no = stu_no;
    }

    public void setStu_pwd(String stu_pwd) {
        this.stu_pwd = stu_pwd;
    }

    public void setStu_name(String stu_name) {
        this.stu_name = stu_name;
    }

    public void setStu_id(String stu_id) {
        this.stu_id = stu_id;
    }

    public void setPaper_id(int paper_id) {
        this.paper_id = paper_id;
    }

    public void setTotal_grade(int total_grade) {
        this.total_grade = total_grade;
    }

    public void setStu_pic(String stu_pic) {
        this.stu_pic = stu_pic;
    }

    @Override
    public String toString() {
        return "Student{" +
                "stu_no='" + stu_no + '\'' +
                ", stu_pwd='" + stu_pwd + '\'' +
                ", stu_name='" + stu_name + '\'' +
                ", stu_id='" + stu_id + '\'' +
                ", paper_id=" + paper_id +
                ", total_grade=" + total_grade +
                ", stu_pic='" + stu_pic + '\'' +
                '}';
    }
}
