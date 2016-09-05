package com.example.user.writetrack;

import android.app.Application;

/**
 * Created by user on 05/09/2016.
 */
public class MainApplication extends Application {

    DBHandler db;

    @Override
    public void onCreate() {
        super.onCreate();
        db = new DBHandler(this);
    }

}
