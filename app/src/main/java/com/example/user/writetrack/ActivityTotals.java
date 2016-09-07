package com.example.user.writetrack;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by user on 06/09/2016.
 */
public class ActivityTotals extends AppCompatActivity {

    TextView mTotalWordCount;
    TextView mTotalDuration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_totals);

        mTotalWordCount = (TextView) findViewById(R.id.totalWordCount);
        mTotalDuration = (TextView) findViewById(R.id.totalDuration);

        DBHandler db = ((MainApplication) getApplication()).db;
        ArrayList<EntryClass> entries = db.getAllEntries();

        JournalClass journal = new JournalClass();
        String totalWordCount = journal.totalWordCount(entries).toString();
        String totalDuration = journal.totalDuration(entries).toString();

        mTotalWordCount.setText(totalWordCount);
        mTotalDuration.setText(totalDuration);

    }

}
