package com.example.job_wargame2;

public class Player {

    private int score;
    private String name;
    private String location;

    public Player(){
        this.score = 0;
        this.name="";
        this.location="";
    }

    public Player(String name,int score){   //TODO add location
        this.name = name;
        this.score = score;
        this.location = "";
    }

    public void setScore(){
        this.score++;
    }

    public int getScore(){
        return this.score;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Player{" +
                "score=" + score +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}
