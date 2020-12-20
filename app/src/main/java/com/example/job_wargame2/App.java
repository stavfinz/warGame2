package com.example.job_wargame2;

import android.app.Application;

public class App extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        MySP.init(this);
    }
}
