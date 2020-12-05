package com.example.job_wargame2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Entrance_window extends AppCompatActivity {

    private Button entrance_BTN_startNewGame;
    private Button entrance_BTN_scoreTable;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrance_window);
        findViews();
        startNewGame();
        topTenWindow();

    }

    private void topTenWindow() {
        entrance_BTN_scoreTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(Entrance_window.this, TopTen.class);
                startActivity(myIntent);
                finish();
            }
        });
    }

    private void startNewGame() {
        entrance_BTN_startNewGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(Entrance_window.this, MainActivity.class);
                startActivity(myIntent);
                finish();
            }
        });
    }

    private void findViews() {
        entrance_BTN_scoreTable = findViewById(R.id.entrance_BTN_scoreTable);
        entrance_BTN_startNewGame = findViewById(R.id.entrance_BTN_startNewGame);
    }
}