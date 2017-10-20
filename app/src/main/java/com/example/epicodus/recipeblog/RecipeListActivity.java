package com.example.epicodus.recipeblog;

import android.support.annotation.BinderThread;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class RecipeListActivity extends AppCompatActivity {

    @Bind(R.id.listView) ListView mListView;
    public ArrayList<Recipe> recipes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_list);



        ButterKnife.bind(this);


//        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                String recipe= ((TextView)view).getText().toString();
//                Toast.makeText(RecipeListActivity.this, recipe, Toast.LENGTH_SHORT).show();
//
//            }
//        });

        getRecipes("");
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
                        String[] recipeNames = new String[recipes.size()];
                        for (int i = 0; i < recipeNames.length; i++) {
                            recipeNames[i] = recipes.get(i).getmRecipeName();
                        }
                        ArrayAdapter adapter = new ArrayAdapter(RecipeListActivity.this,
                                android.R.layout.simple_list_item_1, recipeNames);
                        mListView.setAdapter(adapter);

                        for (Recipe recipe: recipes){
                            Log.d("Name", " "+recipe.getmRecipeName());
                            Log.d("Time ", " "+recipe.getmTotalTime());
                            Log.d("Ingedients", " "+recipe.getmIngedients());
                        }
                    }
                });


            }
        });
    }
}
