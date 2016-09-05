package com.example.user.writetrack;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by user on 05/09/2016.
 */
public class ActivityJournal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journal);

        final DBHandler db = ((MainApplication)getApplication()).db;

        ListView listView1 = (ListView) findViewById(R.id.entries);

        ArrayList<EntryClass> entries = db.getAllEntries();

        ArrayAdapter<EntryClass> adapter = new ArrayAdapter<EntryClass>(this,
                android.R.layout.simple_list_item_1, entries);

        listView1.setAdapter(adapter);

    }
}
