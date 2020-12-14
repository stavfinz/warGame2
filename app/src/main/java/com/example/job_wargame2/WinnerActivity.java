package com.example.job_wargame2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class WinnerActivity extends AppCompatActivity {

    public static final String WINNER = "WINNER";

    private TextView winnerActivity_LBL_winner;
    private Button activityWinner_BTN_startOver;
    private Button activityWinner_BTN_exit;
    private String gameResult;
    private ImageView winner_PNG_background;
    private MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winner);
        findViews();
        initViews();
        Glide.with(this).
                load("https://image.freepik.com/free-vector/abstract-metallic-red-black-background-with-contrast-stripes_71775-863.jpg").
                into(this.winner_PNG_background);
    }

    private void initViews() {
        playSound(gameResult);
        winnerActivity_LBL_winner.setText(gameResult);
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

    private void playSound(String winnerResult) {
        if (gameResult.compareTo("BATMAN")==0||gameResult.compareTo("SPIDERMAN")==0 )
            mp= MediaPlayer.create(this,R.raw.ta_da);
        else
            mp= MediaPlayer.create(this,R.raw.its_a_tie);
        mp.start();
    }
    private void findViews() {
        gameResult=getIntent().getStringExtra(WINNER);
        winnerActivity_LBL_winner = findViewById(R.id.activityWinner_LBL_winner);
        activityWinner_BTN_startOver = findViewById(R.id.activityWinner_BTN_startOver);
        activityWinner_BTN_exit = findViewById(R.id.activityWinner_BTN_exit);
        winner_PNG_background=findViewById(R.id.winner_PNG_background);
    }
}