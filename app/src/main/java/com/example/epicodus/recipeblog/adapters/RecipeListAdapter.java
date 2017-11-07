package com.example.epicodus.recipeblog.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.annotation.BinderThread;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.epicodus.recipeblog.R;
import com.example.epicodus.recipeblog.models.Recipe;
import com.example.epicodus.recipeblog.ui.RecipeDetailActivity;
import com.example.epicodus.recipeblog.ui.RecipeDetailFragment;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class RecipeListAdapter extends RecyclerView.Adapter<RecipeListAdapter.RecipeViewHolder>{
    private static final int MAX_WIDTH = 200;
    private static final int MAX_HEIGHT = 200;

    private ArrayList<Recipe> mRecipes = new ArrayList<>();
    private Context mContext;

    public RecipeListAdapter(Context context, ArrayList<Recipe> recipes){
        mContext = context;
        mRecipes = recipes;
    }

    @Override
    public RecipeListAdapter.RecipeViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_list_item, parent, false);
        RecipeViewHolder viewHolder = new RecipeViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecipeListAdapter.RecipeViewHolder holder, int position){
        holder.bindRecipe(mRecipes.get(position));
    }

    @Override
    public int getItemCount(){
        return mRecipes.size();
    }

    public class RecipeViewHolder  extends RecyclerView.ViewHolder implements View.OnClickListener{
        @Bind(R.id.recipeImageView) ImageView mRecipeImageView;
        @Bind(R.id.recipeNameTextView) TextView mRecipeName;
        @Bind(R.id.timeTextView) TextView mTimeTextView;
        @Bind(R.id.ratingTextView) TextView mRatingTextView;

        private Context mContext;
        private int mOrientation;

        public RecipeViewHolder(View itemView){
            super(itemView);
            ButterKnife.bind(this, itemView);

            mContext= itemView.getContext();
            mOrientation = itemView.getResources().getConfiguration().orientation;

            if (mOrientation == Configuration.ORIENTATION_LANDSCAPE){
                createDetailFragment(0);
            }
            itemView.setOnClickListener(this);
        }

        private void createDetailFragment(int position){
            RecipeDetailFragment detailFragment = RecipeDetailFragment.newInstance(mRecipes, position);
            FragmentTransaction ft = ((FragmentActivity) mContext).getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.recipeDetailContainer, detailFragment);
            ft.commit();
        }

        public void bindRecipe(Recipe recipe){

            mRecipeName.setText(recipe.getmRecipeName());
            mTimeTextView.setText(String.valueOf(recipe.getmTotalTime()));
            mRatingTextView.setText(String.valueOf(recipe.getmRating()));
            Picasso.with(mContext).load(recipe.getmImage())
                    .resize(MAX_WIDTH, MAX_HEIGHT)
                    .centerCrop()
                    .into(mRecipeImageView);
        }

        @Override
        public void onClick(View v){
            int itemPosition = getLayoutPosition();
            if (mOrientation == Configuration.ORIENTATION_LANDSCAPE){
                createDetailFragment(itemPosition);
            }else{
                Intent intent = new Intent(mContext, RecipeDetailActivity.class);
                intent.putExtra("position", itemPosition + "");
                intent.putExtra("recipes", Parcels.wrap(mRecipes));
                mContext.startActivity(intent);
            }

        }


    }
}
