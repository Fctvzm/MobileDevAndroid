package com.example.assem.supercook;

import java.io.Serializable;

/**
 * Created by Assem on 26.11.2017.
 */

public class Ingredient implements Serializable {

    private String name;
    private String portion;

    public Ingredient () {}

    public Ingredient (String name, String portion) {
        this.name = name;
        this.portion = portion;
    }

    public String getName() {return this.name;}
    public String getPortion() {return this.portion;}

    public void setName(String name) {this.name = name;}
    public void setPortion(String portion) {this.portion = portion;}

}
