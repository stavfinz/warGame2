package com.example.job_wargame2;

public class Player {

    private int score;

    public Player(){
        this.score = 0;
    }

    public void setScore(){
        this.score++;
    }

    public int getScore(){
        return this.score;
    }
}
