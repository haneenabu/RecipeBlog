package com.example.epicodus.recipeblog.ui;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.epicodus.recipeblog.Constants;
import com.example.epicodus.recipeblog.R;
import com.example.epicodus.recipeblog.adapters.RecipeListAdapter;
import com.example.epicodus.recipeblog.models.Recipe;
import com.example.epicodus.recipeblog.services.YummlyService;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class RecipeListActivity extends AppCompatActivity {

    @Bind(R.id.recylclerViewer) RecyclerView mRecyclerView;
    private RecipeListAdapter mAdapter;
    private SharedPreferences mSharedPreferences;
    private String mRecentFoods;

    public ArrayList<Recipe> recipes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_list);

        ButterKnife.bind(this);


        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mRecentFoods = mSharedPreferences.getString(Constants.PREFERENCES_FOOD_KEY, null);
        if (mRecentFoods != null){
            getRecipes(mRecentFoods);
        }
    }
    private void getRecipes(String food){
        final YummlyService yummlyService = new YummlyService();

        yummlyService.findRecipes(food, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response)  {
                recipes = yummlyService.processResults(response);

                RecipeListActivity.this.runOnUiThread(new Runnable() {

                    @Override
                    public void run() {

                        mAdapter = new RecipeListAdapter(getApplicationContext(), recipes);
                        mRecyclerView.setAdapter(mAdapter);
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(RecipeListActivity.this);
                        mRecyclerView.setLayoutManager(layoutManager);
                        mRecyclerView.setHasFixedSize(true);
                    }
                });


            }
        });
    }
}
