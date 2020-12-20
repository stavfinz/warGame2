package com.example.job_wargame2;

import android.util.Log;

import java.util.Arrays;
import java.util.Random;

public class WarGame {
    public static final int MAX_ROUNDS=10;
    public static final int NUM_OF_PLAYERS=2;
    public static final int CARDS_FOR_PLAYERS=52/NUM_OF_PLAYERS;

    private Card deckOfCards[][]= new Card[NUM_OF_PLAYERS][CARDS_FOR_PLAYERS];
    private Player [] players = new Player[NUM_OF_PLAYERS];
    private int round;

    /**
     * Creating a game by:
     * Setting player's score into Zero,
     * Shuffling the deck
     */
    public WarGame(){
        this.round=1;
        for(int i = 0 ; i < NUM_OF_PLAYERS ; i++)
           this.players[i] = new Player();
        shuffleCards();
    }

    /**
     * Shuffling the deck and giving to each player the same amount of cards
     */
    public void shuffleCards(){
        boolean usedCards[] = new boolean[NUM_OF_PLAYERS*CARDS_FOR_PLAYERS+1];
        Random r = new Random();
        int rand;
        Arrays.fill(usedCards, false);
        for (int i = 0; i < NUM_OF_PLAYERS; i++) {
            for (int j = 0; j < CARDS_FOR_PLAYERS; j++) {
                do {
                    rand=r.nextInt(52) + 1;
                    deckOfCards[i][j]=createCardByRandomNumber(rand);
                } while (usedCards[rand]);
                usedCards[rand]=true;
            }
        }
    }

    public Card[][] getDeckOfCards() {
        return deckOfCards;
    }

    public int getRound() {
        return round;
    }

    public int getScorePlayer1() {
        return players[0].getScore();
    }

    public int getScorePlayer2() {
        return players[1].getScore();
    }

    public void setScorePlayer1() {
        this.players[0].setScore();
    }

    public void setScorePlayer2() {
        this.players[1].setScore();
    }

    public double getLatitudePlayer1(){
        return this.players[0].getLatitude();
    }
    public double getLongitudePlayer1(){
        return this.players[0].getLongitude();
    }

    public double getLatitudePlayer2(){
        return this.players[1].getLatitude();
    }
    public double getLongitudePlayer2(){
        return this.players[1].getLongitude();
    }

    public void setLocationPlayers(double latitude,double longitude){

        this.players[0].setLatitude(latitude);
        this.players[0].setLongitude(longitude);
        this.players[1].setLatitude(latitude);
        this.players[1].setLongitude(longitude);
    }

    public void nextRound() {
        this.round++;
    }

    /**
     * Getting a randomized  number between 1-52(because the deck has 52 cards)
     * Checking which number and kind the randomized number should be from the deck.
     * Creating a card with the number and kind that found.
     * @param randomNumber
     * @return Card
     */
    public Card createCardByRandomNumber(int randomNumber) {
        Card card=new Card();
        if (randomNumber >= 1 && randomNumber <= 13) {//Clubs
            card.setValue(randomNumber);
            card.setType("club");
        }else if (randomNumber >= 14 && randomNumber <= 26) {//Diamond
            card.setValue(randomNumber - 13);
            card.setType("diamond");
        } else if (randomNumber >= 27 && randomNumber <= 39) { //Heart
            card.setValue(randomNumber - 26);
            card.setType("heart");
        } else {
            card.setValue(randomNumber - 39);
            card.setType("spade");
        }
        return card;
    }

    /**
     * Checking which card is higher(stronger)
     * @param card1
     * @param card2
     * @return 1: greater , -1: smaller
     */
    public int score(Card card1,Card card2) {
        if(card1.compareByValue(card2)==0)
            return card1.compareByType(card2);
        return card1.compareByValue(card2);
    }

}
