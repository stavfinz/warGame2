package com.example.job_wargame2;

import java.util.ArrayList;

public class Card {

    private ArrayList<String> typeWidth=new ArrayList<>(4);
    private String type;
    private int value;

    public Card(){
        this.value=-1;
        this.type="";
        typeWidth.add("club");
        typeWidth.add("diamond");
        typeWidth.add("heart");
        typeWidth.add("spade");
    }

    public int getValue() {
        return value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int compareByValue(Card cardToCompare){
        if (this.value == cardToCompare.value)
            return 0;
        else if (this.value > cardToCompare.value)
            return 1;
        else
            return -1;
    }

    public int compareByType(Card cardToCompare){
        if (typeWidth.indexOf(this.type) == typeWidth.indexOf(cardToCompare.type))
            return 0;
        else if (typeWidth.indexOf(this.type) > typeWidth.indexOf(cardToCompare.type))
            return 1;
        else
            return -1;
    }
}
