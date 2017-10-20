package com.example.epicodus.recipeblog;

/**
 * Created by Guest on 10/20/17.
 */

public class Recipe {
    private String mRecipeName;
    private int mTotalTime;
    private String[] mIngedients;
    private int mRating;

    public Recipe(String recipeName, int totalTime, String[] ingerdients, int rating){
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

    public String[] getmIngedients() {
        return mIngedients;
    }

    public int getmRating() {
        return mRating;
    }
}
