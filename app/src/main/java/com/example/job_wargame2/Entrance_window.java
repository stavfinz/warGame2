package com.example.job_wargame2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class Entrance_window extends AppCompatActivity {

    private TextView main_war_game_TXT;
    private Button activityWinner_BTN_startOver;
    private Button activityWinner_BTN_exit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrance_window);

    }
}