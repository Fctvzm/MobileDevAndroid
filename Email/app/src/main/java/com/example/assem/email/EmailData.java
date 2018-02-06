package com.example.assem.email;

import android.support.annotation.NonNull;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Assem on 20.09.2017.
 */

public class EmailData implements java.io.Serializable, Comparable<EmailData>{
    public String sender;
    public Date date;
    public String title;
    public String content;

    DateFormat format = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH);

    public EmailData (String sender, String date, String title, String content) throws ParseException {
        this.sender = sender;
        this.date = format.parse(date);
        this.title = title;
        this.content = content;
    }

    public String toString(){
        return sender + "\n" + date + "\n" + title + "\n" + content;
    }


    @Override
    public int compareTo(@NonNull EmailData emailData) {
        return this.title.compareTo(emailData.title);
    }

    public static Comparator<EmailData> dateComparator
            = new Comparator<EmailData>() {

        @Override
        public int compare(EmailData t1, EmailData t2) {
            return t1.date.compareTo(t2.date);
        }

    };
}
