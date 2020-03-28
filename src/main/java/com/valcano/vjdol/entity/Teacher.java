package com.valcano.vjdol.entity;

public class Teacher {
    private String teacher_no;
    private String teacher_pwd;
    private String teacher_name;

    public Teacher(String teacher_no, String teacher_name, String teacher_pwd) {
        this.teacher_no = teacher_no;
        this.teacher_pwd = teacher_pwd;
        this.teacher_name = teacher_name;
    }

    public Teacher() {
    }

    public String getTeacher_name() {
        return teacher_name;
    }

    public void setTeacher_name(String teacher_name) {
        this.teacher_name = teacher_name;
    }

    public String getTeacher_no() {
        return teacher_no;
    }

    public String getTeacher_pwd() {
        return teacher_pwd;
    }

    public void setTeacher_no(String teacher_no) {
        this.teacher_no = teacher_no;
    }

    public void setTeacher_pwd(String teacher_pwd) {
        this.teacher_pwd = teacher_pwd;
    }
}
