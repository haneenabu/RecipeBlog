package com.example.epicodus.recipeblog;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class RecipeListActivity extends AppCompatActivity {
    private String[] recipes = new String[] {"Mac & Cheese", "Greek Salad", "Roasted Chicken", "Fluffy Pancakes", "Veggie Lasagna"};
    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_list);

        mListView = (ListView) findViewById(R.id.listView);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, recipes);
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
