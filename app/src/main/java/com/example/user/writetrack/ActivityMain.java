package com.example.user.writetrack;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by user on 05/09/2016.
 */
public class ActivityMain extends AppCompatActivity {

    Button toEntry;
    Button toJournal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final DBHandler db = ((MainApplication)getApplication()).db;

        toEntry = (Button)findViewById(R.id.toEntry);

        toEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ActivityMain.this, ActivityEntry.class));
            }
        });


        toJournal = (Button)findViewById(R.id.toJournal);

        toJournal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ActivityMain.this, ActivityJournal.class));
            }
        });


    }


}
