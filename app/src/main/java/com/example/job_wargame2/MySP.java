package com.example.job_wargame2;

import android.content.Context;
import android.content.SharedPreferences;

import static com.example.job_wargame2.Constants.SP_FILE;

public class MySP {


    private static MySP instance;
    private SharedPreferences prefs;
    private MySP(Context context) {
        prefs = context.getSharedPreferences(SP_FILE,Context.MODE_PRIVATE);
    }

    public static void init(Context context){
        if(instance == null)
            instance = new MySP(context.getApplicationContext());
    }

    public void putString(String key,String value){
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(key,value);
        editor.apply();
    }

    public String getString(String key,String def){
        return prefs.getString(key,def);
    }

    public void removeKey(String key){
        SharedPreferences.Editor editor = prefs.edit();
        editor.remove(key);
        editor.apply();
    }

    public static MySP getInstance() {
        return instance;
    }
}
