package com.example.stream.demo.models;

public class CustomMessage {
    private String data;
    private Long time;

    public CustomMessage(String data, Long time) {
        this.data = data;
        this.time = time;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "CustomMessage{" +
                "data='" + data + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
