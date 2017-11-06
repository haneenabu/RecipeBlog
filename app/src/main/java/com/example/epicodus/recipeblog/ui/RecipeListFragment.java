package com.example.epicodus.recipeblog.ui;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Fragment;
import android.preference.PreferenceManager;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

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


public class RecipeListFragment extends Fragment {

    @Bind(R.id.recyclerViewlist) RecyclerView mRecyclerView;

    private RecipeListAdapter mAdapter;
    public ArrayList<Recipe> mRecipes = new ArrayList<>();
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;
    private String mRecentFood;


    public RecipeListFragment() {}

    @Override
    public void onCreate(Bundle savedInstances){
        super.onCreate(savedInstances);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        mEditor = mSharedPreferences.edit();

        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recipe_list, container, false);
        ButterKnife.bind(this, view);

        mRecentFood = mSharedPreferences.getString(Constants.PREFERENCES_FOOD_KEY, null);
        if (mRecentFood != null){
            getRecipes(mRecentFood);
        }
        // Inflate the layout for this fragment
        return view;
    }

    public void getRecipes(String food){
        final YummlyService yummlyService = new YummlyService();

        yummlyService.findRecipes(food, new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                mRecipes = yummlyService.processResults(response);

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mAdapter = new RecipeListAdapter(getActivity(), mRecipes);

                        mRecyclerView.setAdapter(mAdapter);
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());

                        mRecyclerView.setLayoutManager(layoutManager);
                        mRecyclerView.setHasFixedSize(true);
                    }
                });
            }
        });

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.main, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    private void addToSharedPreferences(String food){
        mEditor.putString(Constants.PREFERENCES_FOOD_KEY, food).apply();
    }

}
