package com.example.user.writetrack;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

/**
 * Created by user on 05/09/2016.
 */
public class ActivityEntry extends AppCompatActivity {

    static TextView mDate;
    EditText mWordCount;
    EditText mDuration;
    Button mSave;

//    Date picker class
    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar cal = Calendar.getInstance();
            int year = cal.get(Calendar.YEAR);
            int month = cal.get(Calendar.MONTH);
            int day = cal.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            // Do something with the date chosen by the user
        }
    }

    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(this.getFragmentManager(), "datePicker");
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
        final Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        mDate.setText(day + "/" + (month + 1) + "/" + year);



        mSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Integer date = Integer.parseInt(mDate.getText().toString());
                Integer wordCount = Integer.parseInt(mWordCount.getText().toString());
                Integer duration = Integer.parseInt(mDuration.getText().toString());

                EntryClass entry = new EntryClass(date, wordCount, duration);
                db.addEntry(entry);
                Toast.makeText(getApplicationContext(), "Saved Successfully", Toast.LENGTH_LONG).show();

            }

        });


    }



}
