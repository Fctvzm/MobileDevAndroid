package com.example.assem.firebase_lab9;

/**
 * Created by Assem on 23.10.2017.
 */

public class Message {
    private String text;
    private String name;
    public boolean isMine;

    public Message() {
    }

    public Message(String text, String name, boolean isMine) {
        this.text = text;
        this.name = name;
        this.isMine = isMine;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
