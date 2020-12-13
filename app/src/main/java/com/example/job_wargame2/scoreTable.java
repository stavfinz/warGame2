package com.example.job_wargame2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.SupportMapFragment;
import com.google.gson.Gson;

import static com.example.job_wargame2.Constants.SP_FILE;

public class scoreTable extends AppCompatActivity {


    private Fragment_List fragment_list;
    private Fragment_Map fragment_map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_table);

        fragment_list = new Fragment_List();
        fragment_list.setCallBack_top(callBack_top);

        getSupportFragmentManager().beginTransaction().add(R.id.scoreTable_LAY_topTen,fragment_list).commit();

        fragment_map = new Fragment_Map();
        fragment_map.mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapAPI);
        getSupportFragmentManager().beginTransaction().add(R.id.scoreTable_LAY_location,fragment_map).commit();
        findViews();
        initViews();
    }

    private void initViews() {
        SharedPreferences prefs = getSharedPreferences(SP_FILE, MODE_PRIVATE);
        String winner = prefs.getString("whoWon","No name defined");
        String top = prefs.getString("TopTen","empty");
        TopTen topTen ;
        Gson gson = new Gson();
        if(top.compareTo("empty") == 0)
            topTen = new TopTen();
        else
            topTen = gson.fromJson(top,TopTen.class);
        //Log.d("pttt",topTen.toString());
        fragment_list.setTopTenPlayers(topTen);
        //fragment_list.setNamesToButtons();

    }

    private void findViews() {

    }

    private CallBack_Top callBack_top = new CallBack_Top() {
        @Override
        public void displayLocation(double latitude,double longitude) {
            fragment_map.showLocation(latitude,longitude);
        }
    };
}