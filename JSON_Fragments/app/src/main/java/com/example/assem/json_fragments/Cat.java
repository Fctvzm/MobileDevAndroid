package com.example.assem.json_fragments;

import java.util.ArrayList;

/**
 * Created by Assem on 10.10.2017.
 */

public class Cat {
    String name;
    String info;
    String urlImage;

    public static ArrayList<Cat> cats;

    public Cat(String name, String info, String urlImage) {
        this.name = name;
        this.info = info;
        this.urlImage = urlImage;
    }

    public String getName () {
        return this.name;
    }

    public String getUrlImage () {
        return this.urlImage;
    }

    public String getInfo (){
        return this.info;
    }
}
