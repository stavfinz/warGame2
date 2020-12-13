package com.example.job_wargame2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class WinnerActivity extends AppCompatActivity {

    public static final String WINNER = "WINNER";

    private TextView winnerActivity_LBL_winner;
    private Button activityWinner_BTN_startOver;
    private Button activityWinner_BTN_exit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winner);
        findViews();
        winnerActivity_LBL_winner.setText(getIntent().getStringExtra(WINNER));
        activityWinner_BTN_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        activityWinner_BTN_startOver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(WinnerActivity.this, MainActivity.class);
                myIntent.putExtra(MainActivity.LATITUDE,Entrance_window.latitude);
                myIntent.putExtra(MainActivity.LONGITUDE,Entrance_window.longitude);
                startActivity(myIntent);
                finish();
            }
        });
    }

    private void findViews() {
        winnerActivity_LBL_winner = findViewById(R.id.activityWinner_LBL_winner);
        activityWinner_BTN_startOver = findViewById(R.id.activityWinner_BTN_startOver);
        activityWinner_BTN_exit = findViewById(R.id.activityWinner_BTN_exit);
    }
}