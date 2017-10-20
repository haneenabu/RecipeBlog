package com.example.epicodus.recipeblog;

import android.support.annotation.BinderThread;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class RecipeListActivity extends AppCompatActivity {
    private String[] recipesTitle = new String[] {"Mac & Cheese", "Greek Salad", "Roasted Chicken", "Fluffy Pancakes", "Veggie Lasagna"};
    private String[] recipes = new String[]{"1. Bring the noodles to a boil", "1. Cut the veggies", "1. Roast the Chicken", "1.Mix the Pancake Mix", "1. PreHeat the Oven"};


    @Bind(R.id.listView) ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_list);

        ButterKnife.bind(this);

        RecipesArrayAdapter adapter = new RecipesArrayAdapter(this, android.R.layout.simple_list_item_1, recipesTitle, recipes);
        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String recipe= ((TextView)view).getText().toString();
                Toast.makeText(RecipeListActivity.this, recipe, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
