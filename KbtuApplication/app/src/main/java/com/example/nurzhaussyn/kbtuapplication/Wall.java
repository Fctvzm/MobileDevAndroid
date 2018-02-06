package com.example.nurzhaussyn.kbtuapplication;

import android.support.annotation.NonNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Assem on 05.10.2017.
 */

public class Wall implements Comparable<Wall>{
    private String content;
    private String title;
    private Date date;
    private String url;
    private SimpleDateFormat spf=new SimpleDateFormat("MMM dd, yy");

    public Wall(String title, String content, Date date, String url) {
        this.title = title;
        this.content = content;
        this.date = date;
        this.url = url;
    }

    public static Date convertingDate (String unixSeconds) throws ParseException {
        return new Date(Long.parseLong(unixSeconds) * 1000);
    }

    public String getContent () {return this.content;}
    public String getTitle () {return this.title;}
    public Date getDate () {return this.date;}
    public String str_date() {
        return spf.format(this.date);
    }
    public String getUrl() {return this.url;}
    public String getFirstPart () {
        if(content.length() <= 70){
            return content;
        }
        else
            return content.substring(0, 70) + "...";
    }


    @Override
    public int compareTo(@NonNull Wall wall) {
        return wall.getDate().compareTo(this.getDate());
    }
}
