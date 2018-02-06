package com.example.assem.lab1;

/**
 * Created by Assem on 18.09.2017.
 */

public class Calculate {

    public int first;
    public int second;
    public int result;
    public String operation;

    public void Cal ()
    {
        if (operation.equals("+"))
            result = first + second;
        else if (operation.equals("-"))
            result = first - second;
        else if (operation.equals("*"))
            result = first * second;
        else if (operation.equals("/"))
            result = first / second;
    }

}
