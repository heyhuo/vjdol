package com.valcano.vjdol.entity;

import java.util.Date;

public class Admin {

    private Integer admin_id;

    private String admin_name;

    private String admin_pwd;

    private Date create_time;

    private Date last_edit_last;

    public Admin() {
    }

    public Admin(Integer admin_id, String admin_name, String admin_pwd, Date create_time, Date last_edit_last) {
        this.admin_id = admin_id;
        this.admin_name = admin_name;
        this.admin_pwd = admin_pwd;
        this.create_time = create_time;
        this.last_edit_last = last_edit_last;
    }

    public Admin(String admin_name, String admin_pwd) {
        this.admin_name = admin_name;
        this.admin_pwd = admin_pwd;
    }

    public void setAdmin_id(Integer admin_id) {
        this.admin_id = admin_id;
    }

    public void setAdmin_name(String admin_name) {
        this.admin_name = admin_name;
    }

    public void setAdmin_pwd(String admin_pwd) {
        this.admin_pwd = admin_pwd;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public void setLast_edit_last(Date last_edit_last) {
        this.last_edit_last = last_edit_last;
    }

    public Integer getAdmin_id() {
        return admin_id;
    }

    public String getAdmin_name() {
        return admin_name;
    }

    public String getAdmin_pwd() {
        return admin_pwd;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public Date getLast_edit_last() {
        return last_edit_last;
    }

    @Override
    public String toString() {
        return "Admin {" +
                "admin_id=" + admin_id +
                ", admin_name='" + admin_name + '\'' +
                ", admin_pwd='" + admin_pwd + '\'' +
                ", create_time=" + create_time +
                ", last_edit_last=" + last_edit_last +
                '}';
    }
}
