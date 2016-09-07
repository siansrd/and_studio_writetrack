package com.example.user.writetrack;

import android.app.Activity;
import android.content.Context;
import android.graphics.Movie;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 05/09/2016.
 */
public class ActivityJournal extends AppCompatActivity {

    private ArrayList<EntryClass> entriesWithDateObjs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journal);

        final DBHandler db = ((MainApplication)getApplication()).db;

        ListView listView1 = (ListView) findViewById(R.id.entries);

        ArrayList<EntryClass> entries = db.getAllEntries();
        JournalClass journal = new JournalClass();
        entriesWithDateObjs = journal.entriesWithDateObjs(entries);


        //create our new array adapter
        ArrayAdapter<EntryClass> adapter = new EntryArrayAdapter(this, 0, entriesWithDateObjs);

        ListView listView = (ListView) findViewById(R.id.entries);
        listView.setAdapter(adapter);


    }


}
