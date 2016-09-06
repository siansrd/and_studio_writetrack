package com.example.user.writetrack;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import java.sql.Date;
import java.util.Calendar;

/**
 * Created by user on 05/09/2016.
 */
public class ActivityEntry extends AppCompatActivity {

    TextView mDate;
    EditText mWordCount;
    EditText mDuration;
    Button mSave;
    Date mSelectedDate;
    private DatePickerDialog datePickerDialog;


    public void showDatePickerDialog(View v) {
        datePickerDialog.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry);

        final DBHandler db = ((MainApplication)getApplication()).db;

        mDate  = (TextView) findViewById(R.id.editDate);
        mWordCount  = (EditText) findViewById(R.id.editWordCount);
        mDuration  = (EditText) findViewById(R.id.editDuration);
        mSave   = (Button) findViewById(R.id.save);
        Calendar newCalendar = Calendar.getInstance();
        mSelectedDate = new Date (newCalendar.getTimeInMillis());

        Log.d("WriteTrack:", "onCreate date " + mSelectedDate.toString() + " has been created");

        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                mSelectedDate.setTime(newDate.getTimeInMillis());
                mDate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));


        mSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                String date = mSelectedDate.toString();
                Integer wordCount = Integer.parseInt(mWordCount.getText().toString());
                Integer duration = Integer.parseInt(mDuration.getText().toString());

                EntryClass entry = new EntryClass(date, wordCount, duration);
                Log.d("WriteTrack:", "clicked! input date " + mSelectedDate.toString() + " has been created");
                db.addEntry(entry);
                Toast.makeText(getApplicationContext(), "Saved Successfully", Toast.LENGTH_LONG).show();

            }

        });


    }



}
