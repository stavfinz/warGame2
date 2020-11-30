package com.example.job_wargame2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    public static final int DELAY = 1000;
    private ImageView main_PNG_leftCard;
    private ImageView main_PNG_rightCard;
    private TextView main_LBL_scorePlayer1;
    private TextView main_LBL_scorePlayer2;
    private ImageButton main_IMGBTN_play;
    private WarGame game;
    private boolean isClicked = false;//flag that will help to control the clicks on button
    final Handler handler = new Handler();
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
        if(!isClicked){//To lock the press on the play button
            main_IMGBTN_play.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //TODO
                    isClicked=true;
                    playGame(game);
                    startGame();
                }
            });
        }
    }

    /**
     * Playing one round in the game or presenting the winner
     * @param game
     */
    private void playGame(WarGame game) {
        if(game.getRound() <= WarGame.MAX_ROUNDS)
            nextRound();
        else
            presentWinner();
    }

    private void presentWinner() {
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
