package com.example.user.writetrack;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by user on 05/09/2016.
 */
public class DBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "WriteTrack";
    private static final String TABLE_JOURNAL = "Journal";
    private static final String KEY_ID = "id";
    private static final String KEY_DATE = "date";
    private static final String KEY_WORDCOUNT = "wordCount";
    private static final String KEY_DURATION = "duration";

    public DBHandler (Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_JOURNAL_TABLE = " CREATE TABLE " + TABLE_JOURNAL + "("
                + KEY_ID + " INTEGER PRIMARY KEY, " + KEY_DATE + " TEXT, " + KEY_WORDCOUNT + " INTEGER, " + KEY_DURATION + " INTEGER " + ")";
        db.execSQL(CREATE_JOURNAL_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_JOURNAL);
        onCreate(db);
    }


    public void addEntry(Entry entry) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_DATE, entry.getDate());
        values.put(KEY_WORDCOUNT, entry.getWordCount());
        values.put(KEY_DURATION, entry.getDuration());

        db.insert(TABLE_JOURNAL, null, values);
        db.close();
    }

    public Entry getEntry(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_JOURNAL, new String[]{KEY_ID, KEY_DATE, KEY_WORDCOUNT, KEY_DURATION}, KEY_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Integer _id         = Integer.parseInt(cursor.getString(0));
        String date         = cursor.getString(1);
        Integer wordcount   = Integer.parseInt(cursor.getString(2));
        Integer duration    = Integer.parseInt(cursor.getString(3));

        Entry entry = new Entry(_id, date, wordcount, duration);
        return entry;
    }

    public ArrayList<Entry> getAllEntries() {
        ArrayList<Entry> entryList = new ArrayList<Entry>();
        String selectQuery = " SELECT * FROM " + TABLE_JOURNAL;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst ()) {
            do {
                Entry entry = new Entry();
                entry.setId(Integer.parseInt(cursor.getString(0)));
                entry.setDate(cursor.getString(1));
                entry.setWordCount(Integer.parseInt(cursor.getString(2)));
                entry.setDuration(Integer.parseInt(cursor.getString(3)));
                entryList.add(entry);
            } while (cursor.moveToNext());
        }
        return entryList;
    }


    public int updateEntry(Entry entry) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_DATE, entry.getDate());
        values.put(KEY_WORDCOUNT, entry.getWordCount());
        values.put(KEY_DURATION, entry.getDuration());

        return db.update(TABLE_JOURNAL, values, KEY_ID + " = ? ",
                new String[]{String.valueOf(entry.getId())});
    }


    public void deleteEntry (Entry entry) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_JOURNAL, KEY_ID + " = ? ",
                new String[]{String.valueOf(entry.getId())});
        db.close();
    }


    public int getEntriesCount() {
        String countQuery = " SELECT * FROM " + TABLE_JOURNAL;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        return cursor.getCount();
    }






}



