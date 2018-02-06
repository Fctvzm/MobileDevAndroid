package com.example.assem.prescription;

/**
 * Created by Assem on 13.11.2017.
 */

public class Prescription {

    private String name;
    private String dose;
    private int duration;
    private int frequency;
    private String instruction;

    public Prescription (String name, String dose, int frequency, int duration, String instruction) {
        this.name = name;
        this.dose = dose;
        this.frequency = frequency;
        this.duration = duration;
        this.instruction = instruction;
    }

    public String getName () {
        return name;
    }

    public String getDose () {
        return dose;
    }

    public String getInstruction () {
        return instruction;
    }

    public int getFrequency () {
        return frequency;
    }

    public int getDuration () {
        return duration;
    }





}
