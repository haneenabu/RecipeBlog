package com.example.epicodus.recipeblog.adapters;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.widget.ArrayAdapter;



public class RecipesArrayAdapter extends ArrayAdapter{
    private Context context;
    private String[] mRecipeTitleList;
    private String [] mRecipeList;


    public RecipesArrayAdapter(Context context, int resource, String[] mRecipesList, String[]mRecipeList) {
        super(context, resource);
        this.context = context;
        this.mRecipeTitleList = mRecipesList;
        this.mRecipeList = mRecipeList;
    }
    @Override
    public Object getItem(int position){
        String recipeTitle = mRecipeTitleList[position];
        String recipe = mRecipeList[position];
        return String.format("%s \nDirections %s", recipeTitle, recipe);
    }
    @Override
    public int getCount(){
        return mRecipeTitleList.length;
    }
}
