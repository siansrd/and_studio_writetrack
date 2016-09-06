package com.example.user.writetrack;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * Created by user on 05/09/2016.
 */
public class EntryClass {

    int _id;
    Date _date;
    Integer _wordCount;
    Integer _duration;

    public EntryClass() {}

    public EntryClass (int id, Date date, Integer wordCount, Integer duration) {
        this._id = id;
        this._date = date;
        this._wordCount = wordCount;
        this._duration = duration;
    }

    public EntryClass (Date date, Integer wordCount, Integer duration) {
        this._date = date;
        this._wordCount = wordCount;
        this._duration = duration;
    }


    public int getId () {
        return this._id;
    }

    public void setId(int id) {
        this._id = id;
    }

    public Date getDate() {
        return this._date;
    }

    public String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd", Locale.getDefault());
        Date date = getDate();
        return dateFormat.format(date);
    }

    public void setDate(Date date) {
        this._date = date;
    }

    public Integer getWordCount() {
        return this._wordCount;
    }

    public void setWordCount(Integer wordCount) {
        this._wordCount = wordCount;
    }

    public Integer getDuration() {
        return this._duration;
    }

    public void setDuration(Integer duration) {
        this._duration = duration;
    }

    @Override
    public String toString() {
        return this._date + " - " + this._wordCount + " - " + this._duration ;
    }


}
