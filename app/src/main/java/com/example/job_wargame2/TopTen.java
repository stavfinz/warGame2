package com.example.job_wargame2;

import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;



public class TopTen {

    private ArrayList<Player> topPlayers;
    public static final int SIZE = 10;

    public TopTen(){
        this.topPlayers = new ArrayList<Player>();
    }

    public TopTen (ArrayList<Player> topPlayers){
        this.topPlayers = topPlayers;
    }

    public ArrayList<Player> getTopPlayers() {
        return topPlayers;
    }

    public void setTopPlayers(ArrayList<Player> topPlayers) {
        this.topPlayers = topPlayers;
    }

    /**
     * Return TRUE if player added successfully to TOP TEN, else return FALSE.
     * Player's score must be greater or equal then the minimum score of top ten players.
     * @param player
     * @return
     */
    public boolean addPlayer(Player player){
        if(this.topPlayers.isEmpty()){
            Log.d("ptttHelp","list is empty\n\n");
            this.topPlayers.add(player);
            return true;
        }
        if(playerMayEnterTopTen(player)){
            Log.d("ptttHelp","player may enter\n\n");
            if(this.topPlayers.size() == SIZE)
                this.topPlayers.remove(0);
            this.topPlayers.add(player);
            this.topPlayers.sort(new Comparator<Player>() {
                @Override
                public int compare(Player o1, Player o2) {
                    return o2.getScore()-o1.getScore();
                }
            });

            return true;
        }
        return false;
    }

    private boolean playerMayEnterTopTen(Player player) {
        if(this.topPlayers.size()!= SIZE)
            return true;
        else if(player.getScore()>=this.topPlayers.get(0).getScore())
            return true;
        return false;
    }

    @Override
    public String toString() {
        return "TopTen{" +
                "topPlayers=" + topPlayers +
                '}';
    }
}
