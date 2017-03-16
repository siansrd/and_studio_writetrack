package com.example.user.writetrack;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * Created by user on 05/09/2016.
 */
public class Entry {

    int _id;
    String _date;
    Integer _wordCount;
    Integer _duration;
    Date _dateObj;

    public Entry() {}

    public Entry(int id, String date, Integer wordCount, Integer duration) {
        this._id = id;
        this._date = date;
        this._wordCount = wordCount;
        this._duration = duration;
    }

    public Entry(String date, Integer wordCount, Integer duration) {
        this._date = date;
        this._wordCount = wordCount;
        this._duration = duration;
    }


    public Entry(int id, String date, Integer wordCount, Integer duration, Date dateObj ) {
        this._id = id;
        this._date = date;
        this._wordCount = wordCount;
        this._duration = duration;
        this._dateObj = dateObj;
    }


    public int getId () { return this._id; }

    public void setId(int id) { this._id = id; }

    public String getDate() { return this._date; }

    public void setDate(String date) {
        this._date = date;
    }

    public Integer getWordCount() { return this._wordCount; }

    public void setWordCount(Integer wordCount) {
        this._wordCount = wordCount;
    }

    public Integer getDuration() {
        return this._duration;
    }

    public void setDuration(Integer duration) {
        this._duration = duration;
    }

    public Date getDateObj() { return this._dateObj; }

    public void setDateObj(Date dateObj) {
        this._dateObj = dateObj;
    }



    @Override
    public String toString() {
        return this._date + " - " + this._wordCount + " - " + this._duration ;
    }


}
