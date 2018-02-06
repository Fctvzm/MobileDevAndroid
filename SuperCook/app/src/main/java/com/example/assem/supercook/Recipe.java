package com.example.assem.supercook;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Assem on 25.11.2017.
 */

public class Recipe implements Serializable{

    private double ratings;
    private String name;
    private Map<String, String> preparation;
    private Map<String, String> ingredients;
    private int category;
    private int time;
    private int servings;
    private String imgId;
    private String id;

    public Recipe () {}

    public Recipe (double ratings, String name, Map<String, String> preparation, int time,
                   int servings, Map<String, String> ingredients, String imgId, int category, String id) {
        this.name = name;
        this.preparation = preparation;
        this.time = time;
        this.servings = servings;
        this.ratings = ratings;
        this.ingredients = ingredients;
        this.imgId = imgId;
        this.category = category;
        this.id = id;
    }

    public String getName() {return this.name;}
    public int getTime() {return this.time;}
    public double getRatings() {return this.ratings;}
    public Map<String, String> getPreparation() {return this.preparation;}
    public int getServings() {return this.servings;}
    public Map<String, String> getIngredients() {return this.ingredients;}
    public int getCategory() {return this.category;}
    public String getImgId() {return this.imgId;}
    public String getId() {return this.id;}

    public void setName(String name) {this.name = name;}
    public void setTime(int time) {this.time = time;}
    public void setRatings(double point) {this.ratings = point;}
    public void setPreparation(Map<String, String> preparation) {this.preparation = preparation;}
    public void setServings(int servings) {this.servings = servings;}
    public void setIngredients(Map<String, String> ingredients) {this.ingredients = ingredients;}
    public void setImgId(String imgId){this.imgId = imgId;}
    public void setCategory(int category) {this.category = category;}
    public void setId(String id) {this.id = id;}
}
