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
import com.google.firebase.database.Query;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SavedRecipesListActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_recipe_list);

    }


}
