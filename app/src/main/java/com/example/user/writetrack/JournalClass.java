package com.example.user.writetrack;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.Date;
import java.util.ArrayList;

/**
 * Created by user on 05/09/2016.
 */

public class JournalClass {

    public JournalClass() {
    }

   public Integer totalWordCount(ArrayList<EntryClass> entries) {
       Integer total = 0;
       for (EntryClass entry : entries) {
          total += entry.getWordCount();
       }
       return total;
   }


    public Integer totalDuration(ArrayList<EntryClass> entries) {
        Integer total = 0;
        for (EntryClass entry : entries) {
            total += entry.getDuration();
        }
        return total;
    }


    public ArrayList<EntryClass> entriesWithDateObjs (ArrayList<EntryClass> entries) {
        ArrayList<EntryClass> entriesWithDateObjs = new ArrayList<EntryClass>();

        for (EntryClass entry : entries) {
            Integer id          = entry.getId();
            String date         = entry.getDate();
            Integer wordCount   = entry.getWordCount();
            Integer duration    = entry.getDuration();
            Date dateObj        = Date.valueOf(date);

            EntryClass entryWithDateObj = new EntryClass( id, date, wordCount, duration, dateObj );
            entriesWithDateObjs.add(entryWithDateObj);
        }
        return entriesWithDateObjs;
    }


}
