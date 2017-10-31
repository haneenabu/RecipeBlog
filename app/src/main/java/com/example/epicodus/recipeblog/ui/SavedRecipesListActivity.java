package com.example.epicodus.recipeblog.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.example.epicodus.recipeblog.Constants;
import com.example.epicodus.recipeblog.R;
import com.example.epicodus.recipeblog.adapters.FirebaseRecipeListAdapter;
import com.example.epicodus.recipeblog.adapters.FirebaseRecipeViewHolder;
import com.example.epicodus.recipeblog.models.Recipe;
import com.example.epicodus.recipeblog.util.ItemTouchHelperAdapter;
import com.example.epicodus.recipeblog.util.OnStartDragListener;
import com.example.epicodus.recipeblog.util.SimpleItemTouchHelperCallback;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SavedRecipesListActivity extends AppCompatActivity implements OnStartDragListener{

    private DatabaseReference mRecipeReference;
    private FirebaseRecipeListAdapter mFirebaseAdapter;
    private ItemTouchHelper mItemTouchHelper;

    @Bind(R.id.recylclerViewer) RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_recipe_list);
        ButterKnife.bind(this);


        setUpFirebaseAdapter();
    }
    private void setUpFirebaseAdapter(){

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();

        mRecipeReference = FirebaseDatabase
                .getInstance()
                .getReference(Constants.FIREBASE_CHILD_RECIPE)
                .child(uid);


        mFirebaseAdapter = new FirebaseRecipeListAdapter(Recipe.class, R.layout.recipe_list_item_drag, FirebaseRecipeViewHolder.class, mRecipeReference, this, this);



        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mFirebaseAdapter);

        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(mFirebaseAdapter);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(mRecyclerView);
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        mFirebaseAdapter.cleanup();
    }

    @Override
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
        mItemTouchHelper.startDrag(viewHolder);
    }
}
