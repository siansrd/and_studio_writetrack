package com.example.user.writetrack;


import java.util.ArrayList;

/**
 * Created by user on 05/09/2016.
 */

public class JournalClass {

    public JournalClass() {
    }

   public int totalWordCount(ArrayList<EntryClass> entries) {
       int total = 0;
       for (EntryClass entry : entries) {
          total += entry.getWordCount();
       }
       return total;
   }


    public int totalDuration(ArrayList<EntryClass> entries) {
        int total = 0;
        for (EntryClass entry : entries) {
            total += entry.getDuration();
        }
        return total;
    }


}
