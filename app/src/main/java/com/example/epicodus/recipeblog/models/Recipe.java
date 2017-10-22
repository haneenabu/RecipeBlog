package com.example.epicodus.recipeblog.models;

import java.util.ArrayList;
import org.parceler.Parcel;

@Parcel
public class Recipe {
    private String mRecipeName;
    private int mTotalTime;
    private ArrayList<String> mIngedients;
    private int mRating;
    private String mImage;

    public Recipe(){}

    public Recipe(String recipeName, int totalTime, ArrayList<String> ingredients, int rating, String image){
        this.mRecipeName = recipeName;
        this.mTotalTime = totalTime;
        this.mIngedients = ingredients;
        this.mRating= rating;
        this.mImage= getLargeImageURL(image);
    }

    public String getLargeImageURL(String image){
        String largeImageUrl = image.substring(0, image.length() - 4);
        return largeImageUrl;
    }
    public String getmRecipeName() {
        return mRecipeName;
    }

    public int getmTotalTime() {
        return mTotalTime/60;
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
