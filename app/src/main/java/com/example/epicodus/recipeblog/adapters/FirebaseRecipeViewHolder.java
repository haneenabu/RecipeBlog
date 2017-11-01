package com.example.epicodus.recipeblog.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.epicodus.recipeblog.Constants;
import com.example.epicodus.recipeblog.R;
import com.example.epicodus.recipeblog.models.Recipe;
import com.example.epicodus.recipeblog.ui.RecipeDetailActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;

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
        TextView nameTextView = (TextView) mView.findViewById(R.id.recipeName);
        TextView ratingTextView = (TextView) mView.findViewById(R.id.ratingTextView);
        TextView timeTextView = (TextView) mView.findViewById(R.id.timeTextView);
        ImageView imageView = (ImageView) mView.findViewById(R.id.recipeImageView);

        nameTextView.setText(recipe.getmRecipeName());
        ratingTextView.setText("Rating: " + Integer.toString(recipe.getmRating()));
        timeTextView.setText("Time in Mins: "+Integer.toString(recipe.getmTotalTime()));
        Picasso.with(mContext).load(recipe.getmImage())
                .resize(MAX_WIDTH, MAX_HEIGHT)
                .centerCrop()
                .into(imageView);
    }

    @Override
    public void onClick(View view){
        final ArrayList<Recipe> recipes = new ArrayList<>();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_RECIPE);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot: dataSnapshot.getChildren()){
                    recipes.add(snapshot.getValue(Recipe.class));
                }
                int itemPosition = getLayoutPosition();

                Intent intent = new Intent(mContext, RecipeDetailActivity.class);
                intent.putExtra("position", itemPosition + "");
                intent.putExtra("recipes", Parcels.wrap(recipes));

                mContext.startActivity(intent);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


}
