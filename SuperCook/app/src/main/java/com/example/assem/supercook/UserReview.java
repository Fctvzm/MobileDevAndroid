package com.example.assem.supercook;

import java.util.HashMap;

/**
 * Created by Assem on 28.11.2017.
 */

public class UserReview  {
    private double point;
    private String text;
    private String name;

    public UserReview () {

    }

    public UserReview(double point, String text, String name) {
        this.point = point;
        this.text = text;
        this.name = name;
    }

    public String getText() {return this.text;}
    public double getPoint() {return this.point;}
    public String getName() {return this.name;}

    public void setPoint(double point) {this.point = point;}
    public void setText(String text) {this.text = text;}
    public void setName(String name) {this.name = name;}
}
