package com.example.epicodus.recipeblog.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.epicodus.recipeblog.models.Recipe;
import com.google.firebase.database.DatabaseError;

/**
 * Created by haneenabu-khater on 10/26/17.
 */

public class FirebaseRecipeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    private static final int MAX_WIDTH = 200;
    private static final int MAX_HEIGHT = 200;

    View mView;
    Context mContext;

    public FirebaseRecipeViewHolder(View itemView){
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
        itemView.setOnClickListener(this);
    }
    public void bindRecipe(Recipe recipe){

    }

    @Override
    public void onClick(View view){

    }


}
