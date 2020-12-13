package com.example.job_wargame2;

public class Player {

    private int score;
    private String name;
    private double latitude;
    private double longitude;

    public Player(){
        this.score = 0;
        this.name="";
        this.longitude = 0;
        this.latitude = 0;
    }

    public Player(String name,int score,double latitude,double longitude){   //TODO add location
        this.name = name;
        this.score = score;
        this.longitude = longitude;
        this.latitude = latitude;
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

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }


    @Override
    public String toString() {
        return "Player{" +
                "score=" + score +
                ", name='" + name + '\'' +
                ", location ='" +latitude  + ","+longitude +'\'' +
                '}';
    }
}
