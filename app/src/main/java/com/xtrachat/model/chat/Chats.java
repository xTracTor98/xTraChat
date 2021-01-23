package com.xtrachat.model.chat;

public class Chats {
    private String dateTime;
    private String textMessage;
    private String type;
    private String sender;
    private String reciever;

    public Chats() {

    }

    public Chats(String dateTime, String textMessage, String type, String sender, String reciever) {
        this.dateTime = dateTime;
        this.textMessage = textMessage;
        this.type = type;
        this.sender = sender;
        this.reciever = reciever;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getTextMessage() {
        return textMessage;
    }

    public void setTextMessage(String textMessage) {
        this.textMessage = textMessage;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReciever() {
        return reciever;
    }

    public void setReciever(String reciever) {
        this.reciever = reciever;
    }
}
