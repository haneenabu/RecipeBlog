package com.example.epicodus.recipeblog.models;

import java.util.ArrayList;

/**
 * Created by Guest on 10/20/17.
 */

public class Recipe {
    private String mRecipeName;
    private int mTotalTime;
    private ArrayList<String> mIngedients;
    private int mRating;
    private String mImage;

    public Recipe(String recipeName, int totalTime, ArrayList<String> ingerdients, int rating, String image){
        this.mRecipeName = recipeName;
        this.mTotalTime = totalTime;
        this.mIngedients = ingerdients;
        this.mRating= rating;
        this.mImage= image;
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

    public String getmImage() {
        return mImage;
    }
}
