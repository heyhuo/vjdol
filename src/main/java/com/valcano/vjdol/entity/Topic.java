package com.valcano.vjdol.entity;

import java.util.Date;

public class Topic {
    private int topic_id;
    private String topic_title;
    private String topic_content;
    private String input_format;
    private String output_format;
    private String input_sample;
    private String output_sample;
    private int time_limit;
    private int memory_limit;
    private int topic_score;
    private boolean publish_state;
    private int point_num;
    private Date create_time;
    private Date last_edit_time;
    private String author;
    private int accepted;
    private int total_submit;
    private int topic_type;

    public Topic() {
    }

    public int getTime_limit() {
        return time_limit;
    }

    public int getMemory_limit() {
        return memory_limit;
    }

    public int getTopic_score() {
        return topic_score;
    }

    public boolean isPublish_state() {
        return publish_state;
    }

    public void setTime_limit(int time_limit) {
        this.time_limit = time_limit;
    }

    public void setMemory_limit(int memory_limit) {
        this.memory_limit = memory_limit;
    }

    public void setTopic_score(int topic_score) {
        this.topic_score = topic_score;
    }

    public void setPublish_state(boolean publish_state) {
        this.publish_state = publish_state;
    }

    public int getPoint_num() {
        return point_num;
    }

    public void setPoint_num(int point_num) {
        this.point_num = point_num;
    }

    public int getTopic_id() {
        return topic_id;
    }

    public String getTopic_title() {
        return topic_title;
    }

    public String getTopic_content() {
        return topic_content;
    }

    public String getInput_format() {
        return input_format;
    }

    public void setOutput_format(String output_format) {
        this.output_format = output_format;
    }

    public String getOutput_format() {
        return output_format;
    }

    @Override
    public String toString() {
        return "Topic{" +
                "topic_id=" + topic_id +
                ", topic_title='" + topic_title + '\'' +
                ", topic_content='" + topic_content + '\'' +
                ", input_format='" + input_format + '\'' +
                ", output_format='" + output_format + '\'' +
                ", input_sample='" + input_sample + '\'' +
                ", output_sample='" + output_sample + '\'' +
                ", time_limit=" + time_limit +
                ", memory_limit=" + memory_limit +
                ", topic_score=" + topic_score +
                ", publish_state=" + publish_state +
                ", point_num=" + point_num +
                ", create_time=" + create_time +
                ", last_edit_time=" + last_edit_time +
                ", author='" + author + '\'' +
                ", accepted=" + accepted +
                ", total_submit=" + total_submit +
                ", topic_type=" + topic_type +
                '}';
    }

    public String getInput_sample() {
        return input_sample;
    }

    public String getOutput_sample() {
        return output_sample;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public Date getLast_edit_time() {
        return last_edit_time;
    }

    public String getAuthor() {
        return author;
    }

    public int getAccepted() {
        return accepted;
    }

    public int getTotal_submit() {
        return total_submit;
    }

    public int getTopic_type() {
        return topic_type;
    }

    public void setTopic_id(int topic_id) {
        this.topic_id = topic_id;
    }

    public void setTopic_title(String topic_title) {
        this.topic_title = topic_title;
    }

    public void setTopic_content(String topic_content) {
        this.topic_content = topic_content;
    }

    public void setInput_format(String input_format) {
        this.input_format = input_format;
    }


    public void setInput_sample(String input_sample) {
        this.input_sample = input_sample;
    }

    public void setOutput_sample(String output_sample) {
        this.output_sample = output_sample;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public void setLast_edit_time(Date last_edit_time) {
        this.last_edit_time = last_edit_time;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setAccepted(int accepted) {
        this.accepted = accepted;
    }

    public void setTotal_submit(int total_submit) {
        this.total_submit = total_submit;
    }

    public void setTopic_type(int topic_type) {
        this.topic_type = topic_type;
    }


}
