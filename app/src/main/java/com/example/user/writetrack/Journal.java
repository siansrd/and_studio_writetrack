package com.example.user.writetrack;


import java.sql.Date;
import java.util.ArrayList;

/**
 * Created by user on 05/09/2016.
 */

public class Journal {

    public Journal() {
    }

   public Integer totalWordCount(ArrayList<Entry> entries) {
       Integer total = 0;
       for (Entry entry : entries) {
          total += entry.getWordCount();
       }
       return total;
   }


    public Integer totalDuration(ArrayList<Entry> entries) {
        Integer total = 0;
        for (Entry entry : entries) {
            total += entry.getDuration();
        }
        return total;
    }


    public ArrayList<Entry> entriesWithDateObjs (ArrayList<Entry> entries) {
        ArrayList<Entry> entriesWithDateObjs = new ArrayList<Entry>();

        for (Entry entry : entries) {
            Integer id          = entry.getId();
            String date         = entry.getDate();
            Integer wordCount   = entry.getWordCount();
            Integer duration    = entry.getDuration();
            Date dateObj        = Date.valueOf(date);

            Entry entryWithDateObj = new Entry( id, date, wordCount, duration, dateObj );
            entriesWithDateObjs.add(entryWithDateObj);
        }
        return entriesWithDateObjs;
    }


    public static ArrayList<Entry> filterEntriesByDate (Date fromDate, Date toDate, ArrayList<Entry> entries) {

        ArrayList<Entry> filteredEntries = new ArrayList<Entry>();
        for (Entry entry : entries) {
            if (entry.getDateObj().after(fromDate) && entry.getDateObj().before(toDate) ) {
                filteredEntries.add(entry);
            }
        }
        return filteredEntries;
    }


}
