package com.example.job_wargame2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.gson.Gson;

import static com.example.job_wargame2.Constants.SP_FILE;

public class scoreTable extends AppCompatActivity {


    private Fragment_List fragment_list;
    private Fragment_Map fragment_map;
    private TextView scoreTable_TXV_title;
    private ImageView scoreTable_PNG_background;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_table);

        fragment_list = new Fragment_List();
        fragment_list.setCallBack_top(callBack_top);

        getSupportFragmentManager().beginTransaction().add(R.id.scoreTable_LAY_topTen,fragment_list).commit();

        fragment_map = new Fragment_Map();
        getSupportFragmentManager().beginTransaction().add(R.id.scoreTable_LAY_location,fragment_map).commit();
        findViews();

        initViews();
        Glide.with(this).load("https://image.freepik.com/free-vector/abstract-metallic-red-black-background-with-contrast-stripes_71775-864.jpg")
                .into(this.scoreTable_PNG_background);
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
        fragment_list.setTopTenPlayers(topTen);


    }

    private void findViews() {
        scoreTable_PNG_background=findViewById(R.id.scoreTable_PNG_background);
        scoreTable_TXV_title=findViewById(R.id.scoreTable_TXV_title);
    }

    private CallBack_Top callBack_top = new CallBack_Top() {
        @Override
        public void displayLocation(double latitude,double longitude) {
            fragment_map.showLocation(latitude,longitude);
        }
    };
}