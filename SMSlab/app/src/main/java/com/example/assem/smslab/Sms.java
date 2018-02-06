package com.example.assem.smslab;

/**
 * Created by Assem on 07.11.2017.
 */

public class Sms {
    private String text;
    private String number;
    public boolean isMine;

    public Sms() {
    }

    public Sms(String text, String name, boolean isMine) {
        this.text = text;
        this.number = name;
        this.isMine = isMine;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getNumber() {
        return number;
    }

    public void setName(String name) {
        this.number = name;
    }
}
