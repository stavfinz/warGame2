package com.example.job_wargame2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import static com.example.job_wargame2.Constants.SP_FILE;

public class TopTen extends AppCompatActivity {

    private TextView topTen_LBL_winner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_ten);
        findViews();
        initViews();
    }

    private void initViews() {
        SharedPreferences prefs = getSharedPreferences(SP_FILE, MODE_PRIVATE);
        String winner = prefs.getString("whoWon","No name defined");
        Log.d("pttt","winner=" +winner);
        topTen_LBL_winner.setText(winner);
    }

    private void findViews() {
        topTen_LBL_winner = findViewById(R.id.topTen_LBL_winner);
    }
}