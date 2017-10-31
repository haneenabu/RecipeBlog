package com.example.epicodus.recipeblog.adapters;


import android.content.Context;

import com.example.epicodus.recipeblog.models.Recipe;
import com.example.epicodus.recipeblog.util.ItemTouchHelperAdapter;
import com.example.epicodus.recipeblog.util.OnStartDragListener;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

public class FirebaseRecipeListAdapter extends FirebaseRecyclerAdapter<Recipe, FirebaseRecipeViewHolder> implements ItemTouchHelperAdapter {

    private DatabaseReference mRef;
    private OnStartDragListener mOnStartDragListener;
    private Context mContext;

    public FirebaseRecipeListAdapter(Class<Recipe> modelClass, int modelLayout, Class<FirebaseRecipeViewHolder> viewHolderClass, Query ref, OnStartDragListener onStartDragListener, Context context){
        super(modelClass, modelLayout, viewHolderClass, ref);
        mRef = ref.getRef();
        mOnStartDragListener = onStartDragListener;
        mContext = context;
    }

    @Override
    protected void populateViewHolder(FirebaseRecipeViewHolder viewHolder, Recipe model, int position) {
        viewHolder.bindRecipe(model);

    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        notifyItemMoved( fromPosition,  toPosition);
        return false;
    }

    @Override
    public void onItemDismiss(int position) {
        getRef(position).removeValue();

    }


}
