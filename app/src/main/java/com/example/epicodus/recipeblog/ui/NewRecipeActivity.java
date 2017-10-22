package com.example.epicodus.recipeblog.ui;

import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.epicodus.recipeblog.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class NewRecipeActivity extends AppCompatActivity implements OnClickListener {

    @Bind(R.id.editRecipeTitle) EditText mEditRecipeTitle;
    @Bind(R.id.editIngedient1) EditText mEditIngredient1;
    @Bind(R.id.editIngedient2) EditText mEditIngredient2;
    @Bind(R.id.editIngedient3) EditText mEditIngredient3;
    @Bind(R.id.editDirections) EditText mEditDirections;
    @Bind(R.id.addRecipeButton) Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_recipe);

        ButterKnife.bind(this);

        mButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v){
        String title = mEditRecipeTitle.getText().toString();
        String ing1 = mEditIngredient1.getText().toString();
        String ing2 = mEditIngredient2.getText().toString();
        String ing3 = mEditIngredient3.getText().toString();
        String desc = mEditDirections.getText().toString();

        if(title.length() == 0 || ing1.length() ==0 || ing2.length() ==0 || ing3.length() ==0|| desc.length() ==0)
        {
            Toast.makeText(NewRecipeActivity.this, "All fields are required!", Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(NewRecipeActivity.this, "Adding Recipes Coming Soon!!!", Toast.LENGTH_LONG).show();
        }


    }
}
