package com.example.user.writetrack;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by user on 06/09/2016.
 */
public class TotalsActivity extends AppCompatActivity {

    TextView mTotalWordCount;
    TextView mTotalDuration;
    TextView mWordsPerHour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_totals);

        mTotalWordCount = (TextView) findViewById(R.id.totalWordCount);
        mTotalDuration = (TextView) findViewById(R.id.totalDuration);
        mWordsPerHour = (TextView) findViewById(R.id.wordPerHour);

        DBHandler db = ((MainApplication) getApplication()).db;
        ArrayList<Entry> entries = db.getAllEntries();

        Journal journal = new Journal();
        String totalWordCount = journal.totalWordCount(entries).toString();
        String totalDuration = journal.totalDuration(entries).toString();
        Integer wordsPerHour = (journal.totalWordCount(entries) / journal.totalDuration(entries));

        mTotalWordCount.setText(totalWordCount);
        mTotalDuration.setText(totalDuration);
        mWordsPerHour.setText(wordsPerHour.toString());



    }

}
