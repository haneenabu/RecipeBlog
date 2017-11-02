package com.example.epicodus.recipeblog.adapters;


import android.content.Context;
import android.support.v4.view.MotionEventCompat;
import android.view.MotionEvent;
import android.view.View;

import com.example.epicodus.recipeblog.models.Recipe;
import com.example.epicodus.recipeblog.util.ItemTouchHelperAdapter;
import com.example.epicodus.recipeblog.util.OnStartDragListener;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

import java.util.ArrayList;
import java.util.Collections;

public class FirebaseRecipeListAdapter extends FirebaseRecyclerAdapter<Recipe, FirebaseRecipeViewHolder> implements ItemTouchHelperAdapter {

    private DatabaseReference mRef;
    private OnStartDragListener mOnStartDragListener;
    private Context mContext;
    private ChildEventListener mChildEventListener;
    private ArrayList<Recipe> mRecipes = new ArrayList<>();

    public FirebaseRecipeListAdapter(Class<Recipe> modelClass, int modelLayout, Class<FirebaseRecipeViewHolder> viewHolderClass, Query ref, OnStartDragListener onStartDragListener, Context context){
        super(modelClass, modelLayout, viewHolderClass, ref);
        mRef = ref.getRef();
        mOnStartDragListener = onStartDragListener;
        mContext = context;

        mChildEventListener = mRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                mRecipes.add(dataSnapshot.getValue(Recipe.class));
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    protected void populateViewHolder(final FirebaseRecipeViewHolder viewHolder, Recipe model, int position) {
        viewHolder.bindRecipe(model);
        //imageview
        viewHolder.imageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if (MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_DOWN){
                    mOnStartDragListener.onStartDrag(viewHolder);
                }
                return false;
            }
        });

    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        Collections.swap(mRecipes, fromPosition, toPosition);
        notifyItemMoved( fromPosition,  toPosition);
        return false;
    }

    @Override
    public void onItemDismiss(int position) {
        mRecipes.remove(position);
        getRef(position).removeValue();

    }

    private void setIndexInFirebase(){
        for(Recipe recipe: mRecipes){
            int index = mRecipes.indexOf(recipe);
            DatabaseReference ref = getRef(index);
            recipe.setIndex(Integer.toString(index));
            ref.setValue(recipe);
        }
    }
    @Override
    public void cleanup() {
        super.cleanup();
        setIndexInFirebase();
        mRef.removeEventListener(mChildEventListener);
    }

}
