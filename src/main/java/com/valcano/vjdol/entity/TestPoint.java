package com.valcano.vjdol.entity;

public class TestPoint {
    private int point_id;
    private int topic_id;
    private int topic_type;
    private String input_data;
    private String out_data;
    private int point_socre;
    private String input_path;

    public TestPoint() {

    }

    public String getInput_path() {
        return input_path;
    }

    public void setInput_path(String input_path) {
        this.input_path = input_path;
    }

    public int getPoint_id() {
        return point_id;
    }

    public int getTopic_id() {
        return topic_id;
    }

    public int getTopic_type() {
        return topic_type;
    }

    public String getInput_data() {
        return input_data;
    }


    public void setPoint_id(int point_id) {
        this.point_id = point_id;
    }

    public void setTopic_id(int topic_id) {
        this.topic_id = topic_id;
    }

    public void setTopic_type(int topic_type) {
        this.topic_type = topic_type;
    }

    public void setInput_data(String input_data) {
        this.input_data = input_data;
    }

    public String getOut_data() {
        return out_data;
    }

    public void setPoint_socre(int point_socre) {
        this.point_socre = point_socre;
    }

    public int getPoint_socre() {
        return point_socre;
    }

    public void setOut_data(String out_data) {
        this.out_data = out_data;
    }


    @Override
    public String toString() {
        return "TestPoint{" +
                "point_id=" + point_id +
                ", topic_id=" + topic_id +
                ", topic_type=" + topic_type +
                ", input_data='" + input_data + '\'' +
                ", out_data='" + out_data + '\'' +
                ", point_socre=" + point_socre +
                '}';
    }
}
