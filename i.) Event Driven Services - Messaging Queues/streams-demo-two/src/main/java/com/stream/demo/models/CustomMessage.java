package com.stream.demo.models;

public class CustomMessage {
    private String data;
    private String sender;

    public CustomMessage(String data, String sender) {
        this.data = data;
        this.sender = sender;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    @Override
    public String toString() {
        return "CustomMessage{" +
                "data='" + data + '\'' +
                ", sender='" + sender + '\'' +
                '}';
    }
}
