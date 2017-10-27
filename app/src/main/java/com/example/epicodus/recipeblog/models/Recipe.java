package com.example.epicodus.recipeblog.models;

import java.util.ArrayList;
import java.util.List;

import org.parceler.Parcel;

@Parcel
public class Recipe {
     String mRecipeName;
     int mTotalTime;
     List<String> mIngedients = new ArrayList<> ();
     int mRating;
     String mImage;
    private String pushId;

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

    public List<String> getmIngedients() {
        return mIngedients;
    }

    public int getmRating() {
        return mRating;
    }

    public String getmImage() {
        return mImage;
    }

    public String getPushId() {
        return pushId;
    }

    public void setPushId(String pushId) {
        this.pushId = pushId;
    }
}
