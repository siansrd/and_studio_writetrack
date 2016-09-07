package com.example.user.writetrack;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 07/09/2016.
 */
class EntryArrayAdapter extends ArrayAdapter<EntryClass> {

    private Context context;
    private List<EntryClass> entries;

    //constructor, call on creation
    public EntryArrayAdapter(Context context, int resource, ArrayList<EntryClass> objects) {
        super(context, resource, objects);

        this.context = context;
        this.entries = objects;
    }

    //called when rendering the list
    public View getView(int position, View convertView, ViewGroup parent) {

        //get the entry we are displaying
        EntryClass entry = entries.get(position);

        //get the inflater and inflate the XML layout for each item
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.entry_layout, null);

        TextView date       = (TextView) view.findViewById(R.id.date);
        TextView wordCount  = (TextView) view.findViewById(R.id.wordCount);
        TextView duration   = (TextView) view.findViewById(R.id.duration);


        date.setText(String.valueOf(entry.getDate()));
        wordCount.setText(String.valueOf(entry.getWordCount()));
        duration.setText(String.valueOf(entry.getDuration()));

        return view;
    }
}

