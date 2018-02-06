package com.example.assem.database;

import java.util.ArrayList;

/**
 * Created by Assem on 17.10.2017.
 */

public class Person {
    String name;
    int age;
    String gender;
    public static ArrayList<Person> people;

    public Person (String name, String gender, int age){
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    public String getName () {
        return this.name;
    }

    public int getAge () {
        return this.age;
    }

    public String getGender () {
        return this.gender;
    }

    @Override
    public String toString() {
        return name + " " + age + " " + gender;
    }

    public static ArrayList<String> getAllPeople () {
        ArrayList<String> list = new ArrayList<String>();
        for (Person p : people) {
            list.add(p.getName());
            list.add(p.getAge() + "");
            list.add(p.getGender());
        }
        return list;
    }
}
