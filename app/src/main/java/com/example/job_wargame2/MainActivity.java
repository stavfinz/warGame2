package com.example.job_wargame2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import static com.example.job_wargame2.Constants.SP_FILE;

public class MainActivity extends AppCompatActivity {
    public static final String LONGITUDE = "LONGTITUDE";
    public static final String LATITUDE = "LATITUDE";
    public static final int DELAY = 1000;
    private ImageView main_PNG_leftCard;
    private ImageView main_PNG_rightCard;
    private TextView main_LBL_scorePlayer1;
    private TextView main_LBL_scorePlayer2;
    private Button main_IMGBTN_play;
    private Button main_BTN_scoresTable;
    private boolean isClicked = false;//flag that will help to control the clicks on button
    final Handler handler = new Handler();
    private ProgressBar main_BAR_pBar;
    private Button main_BTN_returnToMenu;
    private WarGame game;
    private TopTen topTenPlayers;
    private ImageView main_PNG_background;
    private ImageView main_PNG_player1;
    private ImageView main_PNG_player2;

    private Gson gson = new Gson();

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
        Log.d("pttt","STAV "+getIntent().getDoubleExtra(LATITUDE,-1)+", "+getIntent().getDoubleExtra(LONGITUDE,-1));
        game.setLocationPlayers(getIntent().getDoubleExtra(LATITUDE,-1),getIntent().getDoubleExtra(LONGITUDE,-1));
        topTenPlayers = getCurrnetTopTen();
        findviews();
        Glide.with(this).load("https://image.freepik.com/free-vector/black-red-metal-background_71775-356.jpg").into(this.main_PNG_background);
        Glide.with(this).load("https://www.flaticon.com/premium-icon/icons/svg/1090/1090806.svg").into(this.main_PNG_player1);
        Glide.with(this).load("https://www.flaticon.com/premium-icon/icons/svg/805/805385.svg").into(this.main_PNG_player2);
        initViews();


    }

    /**
     * Return the current TOP TEN of app user.
     * If the app first used ,will return an EMPTY TOP TEN.
     * @return
     */
    private TopTen getCurrnetTopTen() {
        //SharedPreferences prefs = getSharedPreferences(SP_FILE, MODE_PRIVATE);
        String topTen = MySP.getInstance().getString("TopTen","empty");
        if(topTen.compareTo("empty")==0)
            return new TopTen();
        else
            return gson.fromJson(topTen,TopTen.class);
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
                    finish();
                }
            });
        }
        if(!isClicked){
            main_BTN_scoresTable.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent myIntent = new Intent(MainActivity.this, scoreTable.class);
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
        Intent myIntent = new Intent(MainActivity.this, WinnerActivity.class);
        if(whoWon() == null){
            Log.d("ptttHelp","in who won NULL\n\n");
            MySP.getInstance().putString("whoWon", "its a tie");
            myIntent.putExtra(WinnerActivity.WINNER, "its a tie");
        }else{
            if(topTenPlayers.addPlayer(whoWon())){
                Log.d("ptttHelp","Succesfulyy added");
                MySP.getInstance().putString("TopTen",gson.toJson(topTenPlayers));
                MySP.getInstance().putString("whoWon", whoWon().getName());
            }
            myIntent.putExtra(WinnerActivity.WINNER, whoWon().getName());
        }

        topTenPlayers = getCurrnetTopTen();
        Log.d("pttt",topTenPlayers.toString());

        startActivity(myIntent);
        finish();
    }

    private Player whoWon() {
        Player winner;
        if(game.getScorePlayer1()==game.getScorePlayer2())
            return null;
        else if (game.getScorePlayer1()<game.getScorePlayer2()) {
            winner = new Player("BATMAN",game.getScorePlayer2(),game.getLatitudePlayer2(),game.getLongitudePlayer2());
        }
        else{
            winner = new Player("SPIDERMAN",game.getScorePlayer1(),game.getLatitudePlayer1(),game.getLongitudePlayer1());
        }
        return winner;

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

    private void playSound(int id ) {
        MediaPlayer mp= MediaPlayer.create(this,id);
        mp.start();

    }
    private void setCard(WarGame game) {
        playSound(R.raw.swipe_sound);
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
        main_PNG_background=findViewById(R.id.main_PNG_background);
        main_PNG_player1=findViewById(R.id.main_PNG_player1);
        main_PNG_player2=findViewById(R.id.main_PNG_player2);
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