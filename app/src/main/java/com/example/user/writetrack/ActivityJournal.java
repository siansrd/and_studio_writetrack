package com.example.user.writetrack;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


/**
 * Created by user on 05/09/2016.
 */
public class ActivityJournal extends AppCompatActivity {

    private ArrayList<EntryClass> entriesWithDateObjs;
    ArrayAdapter<EntryClass> adapter;
    DBHandler db;
    private DatePickerDialog datePickerDialog;
    ListView mlistView;
    ListView mFilterListView;
    Button mFromDate;
    Button mToDate;
    EditText mWordCount;
    EditText mDuration;
    Button mFilter;
    Date mSelectedFromDate;
    Date mSelectedToDate;
    private DatePickerDialog datePickerDialog1;
    private DatePickerDialog datePickerDialog2;
    ArrayList<EntryClass> mFilteredEntries;


    public void showDatePickerDialog(View v) {
        datePickerDialog2.show();
        datePickerDialog1.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journal);
        db = ((MainApplication)getApplication()).db;


        mFromDate  = (Button) findViewById(R.id.fromDate);
        mToDate  = (Button) findViewById(R.id.toDate);
        mWordCount  = (EditText) findViewById(R.id.editWordCount);
        mDuration  = (EditText) findViewById(R.id.editDuration);
        mFilter   = (Button) findViewById(R.id.filter);
        Calendar newCalendar = Calendar.getInstance();
        mSelectedFromDate = new Date(newCalendar.getTimeInMillis());
        mSelectedToDate = new Date(newCalendar.getTimeInMillis());

        mFilteredEntries = new ArrayList<EntryClass>();


        //        Prepare list to pass into the list view
        ArrayList<EntryClass> entries = db.getAllEntries();
        JournalClass journal = new JournalClass();
        entriesWithDateObjs = journal.entriesWithDateObjs(entries);


//        Create a new array adapter & list view
        adapter = new EntryArrayAdapter(this, 0, entriesWithDateObjs);
        mlistView = (ListView) findViewById(R.id.entries);
        mlistView.setAdapter(adapter);

//        Delete item on long click
        mlistView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                EntryClass deletedEntry = entriesWithDateObjs.remove(position);
                adapter.notifyDataSetChanged();
                db.deleteEntry(deletedEntry);
                Toast.makeText(ActivityJournal.this, "Deleted", Toast.LENGTH_SHORT).show();
                return true;
            }
        });


        datePickerDialog1 = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                mSelectedFromDate.setTime(newDate.getTimeInMillis());
                mFromDate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
            }
        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));


        datePickerDialog2 = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                mSelectedToDate.setTime(newDate.getTimeInMillis());
                mToDate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
            }
        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));



        mFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fromDate = mSelectedFromDate.toString();
                Log.d("WriteTrack:", "clicked! input from date " + fromDate + " has been created");
                String toDate = mSelectedToDate.toString();
                Log.d("WriteTrack:", "clicked! input to date " + toDate + " has been created");

                mlistView.setVisibility(View.GONE);

                mFilteredEntries = JournalClass.filterEntriesByDate(mSelectedFromDate, mSelectedToDate, entriesWithDateObjs);

                adapter = new EntryArrayAdapter(ActivityJournal.this, 0, mFilteredEntries.size() > 0 ? mFilteredEntries : entriesWithDateObjs );
                mFilterListView = (ListView) findViewById(R.id.filteredEntries);
                mFilterListView.setAdapter(adapter);

            }
        });


    }


}
