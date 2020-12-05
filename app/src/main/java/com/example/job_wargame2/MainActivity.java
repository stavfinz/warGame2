package com.example.job_wargame2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import static com.example.job_wargame2.Constants.SP_FILE;

public class MainActivity extends AppCompatActivity {

    public static final int DELAY = 1000;
    private ImageView main_PNG_leftCard;
    private ImageView main_PNG_rightCard;
    private TextView main_LBL_scorePlayer1;
    private TextView main_LBL_scorePlayer2;
    private Button main_IMGBTN_play;
    private Button main_BTN_scoresTable;
    private WarGame game;
    private boolean isClicked = false;//flag that will help to control the clicks on button
    final Handler handler = new Handler();
    private ProgressBar main_BAR_pBar;
    private Button main_BTN_returnToMenu;


    private Runnable runnable = new Runnable() {
        public void run() {
            if(isClicked){
                handler.postDelayed(this, DELAY);
                playGame(game);
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        game=new WarGame();
        findviews();
        initViews();


    }

    private void initViews() {
        main_IMGBTN_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                main_IMGBTN_play.setEnabled(false);
                isClicked = true;
                main_IMGBTN_play.setVisibility(View.GONE);
                main_BTN_returnToMenu.setEnabled(false);
                main_BTN_scoresTable.setEnabled(false);
                playGame(game);
                startGame();
            }
        });
        if(!isClicked){
            main_BTN_returnToMenu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent myIntent = new Intent(MainActivity.this, Entrance_window.class);
                    startActivity(myIntent);
                    finish();
                }
            });
        }
        if(!isClicked){
            main_BTN_scoresTable.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent myIntent = new Intent(MainActivity.this, TopTen.class);
                    startActivity(myIntent);
                    finish();
                }
            });
        }
    }

    /**
     * Playing one round in the game OR presenting the winner
     * @param game
     */
    private void playGame(WarGame game) {
        if(game.getRound() <= WarGame.MAX_ROUNDS) {
            main_BAR_pBar.setProgress(game.getRound());
            nextRound();
        }
        else
            presentWinner();
    }

    private void presentWinner() {
        SharedPreferences prefs = getSharedPreferences(SP_FILE, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("whoWon", whoWon());
        editor.apply();

        String winner = prefs.getString("whoWon","No name defined");
        Log.d("pttt","winner=" +winner);

        Intent myIntent = new Intent(MainActivity.this, WinnerActivity.class);
        myIntent.putExtra(WinnerActivity.WINNER, whoWon());
        startActivity(myIntent);
        finish();
    }

    private String whoWon() {
        if(game.getScorePlayer1()==game.getScorePlayer2())
            return "Its a TIE!";
        else if (game.getScorePlayer1()<game.getScorePlayer2())
            return "BATMAN";
        else
            return "SPIDER MAN";
    }

    private void nextRound() {
        setCard(game);
        setScore(game);
        game.nextRound();
    }

    private void setScore(WarGame game) {
        if (game.score(game.getDeckOfCards()[0][game.getRound()],game.getDeckOfCards()[1][game.getRound()])>0){
            game.setScorePlayer1();
            main_LBL_scorePlayer1.setText(""+(game.getScorePlayer1()));
        }
        else {
            game.setScorePlayer2();
            main_LBL_scorePlayer2.setText(""+(game.getScorePlayer2()));
        }
    }

    private void setCard(WarGame game) {
        int drawableResourceId1, drawableResourceId2;
        drawableResourceId1 = this.getResources().getIdentifier(
                "poker_card_" + game.getDeckOfCards()[0][game.getRound()].getType() +
                        "_" + game.getDeckOfCards()[0][game.getRound()].getValue(), "drawable", this.getPackageName());
        drawableResourceId2 = this.getResources().getIdentifier(
                "poker_card_" + game.getDeckOfCards()[1][game.getRound()].getType() +
                        "_" + game.getDeckOfCards()[1][game.getRound()].getValue(), "drawable", this.getPackageName());
        main_PNG_leftCard.setImageResource(drawableResourceId1);
        main_PNG_rightCard.setImageResource(drawableResourceId2);

    }

    private void findviews() {
        main_LBL_scorePlayer1 = findViewById(R.id.main_LBL_scorePlayer1);
        main_LBL_scorePlayer2 = findViewById(R.id.main_LBL_scorePlayer2);
        main_IMGBTN_play = findViewById(R.id.main_IMGBTN_play);
        main_PNG_leftCard = findViewById(R.id.main_PNG_leftCard);
        main_PNG_rightCard = findViewById(R.id.main_PNG_rightCard);
        main_BAR_pBar = (ProgressBar) findViewById(R.id.main_BAR_prgsBar);
        main_BTN_returnToMenu = findViewById(R.id.main_BTN_returnToMenu);
        main_BTN_scoresTable=findViewById(R.id.main_BTN_scoresTable);
    }

    @Override
    protected void onStart() {
        Log.d("pttt", "onStart");
        super.onStart();
        startGame();

    }

    @Override
    protected void onResume() {
        Log.d("pttt", "onResume");
        super.onResume();


    }

    @Override
    protected void onPause() {
        Log.d("pttt", "onPause");
        super.onPause();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        Log.d("pttt", "onSaveInstanceState");
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onStop() {
        Log.d("pttt", "onStop");
        super.onStop();
        stopGame();

    }

    @Override
    protected void onDestroy() {
        Log.d("pttt", "onDestroy");
        super.onDestroy();
    }



    /**
     * Run the next runnable after DELAY(Play the next round after DELAY automatically)
     */
    private void startGame() {
        handler.postDelayed(runnable, DELAY);
    }

    /**
     *Stop the runnable from running while the activity is ON STOP(wont play next round)
     */
    private void stopGame() {
        handler.removeCallbacks(runnable);
    }
}