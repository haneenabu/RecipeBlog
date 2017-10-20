package com.example.epicodus.recipeblog.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.example.epicodus.recipeblog.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class NewRecipeActivity extends AppCompatActivity {

    @Bind(R.id.editRecipeTitle) EditText mEditRecipeTitle;
    @Bind(R.id.editIngedient1) EditText mEditIngredient1;
    @Bind(R.id.editIngedient2) EditText mEditIngredient2;
    @Bind(R.id.editIngedient3) EditText mEditIngredient3;
    @Bind(R.id.editDirections) EditText mEditDirections;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_recipe);

        ButterKnife.bind(this);



    }
}
