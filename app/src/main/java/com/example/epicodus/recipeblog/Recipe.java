package com.example.epicodus.recipeblog;

import java.util.ArrayList;

/**
 * Created by Guest on 10/20/17.
 */

public class Recipe {
    private String mRecipeName;
    private int mTotalTime;
    private ArrayList<String> mIngedients = new ArrayList<>();
    private int mRating;

    public Recipe(String recipeName, int totalTime, ArrayList<String> ingerdients, int rating){
        this.mRecipeName = recipeName;
        this.mTotalTime = totalTime;
        this.mIngedients = ingerdients;
        this.mRating= rating;
    }

    public String getmRecipeName() {
        return mRecipeName;
    }

    public int getmTotalTime() {
        return mTotalTime;
    }

    public ArrayList<String> getmIngedients() {
        return mIngedients;
    }

    public int getmRating() {
        return mRating;
    }
}
