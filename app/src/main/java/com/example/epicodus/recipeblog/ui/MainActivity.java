package com.example.epicodus.recipeblog.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.epicodus.recipeblog.Constants;
import com.example.epicodus.recipeblog.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

    @Bind(R.id.foodEditText) EditText mFoodEditText;
    @Bind(R.id.findFoodButton) Button mFindFoodButton;
    @Bind(R.id.savedRecipesButtonMain) Button mSavedRecipesButton;
    private String mRecentFoods;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mRecentFoods = mSharedPreferences.getString(Constants.PREFERENCES_FOOD_KEY, null);

        
        mEditor = mSharedPreferences.edit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        mSavedRecipesButton.setOnClickListener(this);
        mFindFoodButton.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener(){
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth){
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null){
                    getSupportActionBar().setTitle("Welcome, " + user.getDisplayName());
                }else{

                }
            }
        };
    }

    private void logout(){
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    @Override
    public void onStart(){
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop(){
        super.onStop();
        if (mAuthListener != null){
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    @Override
    public void onClick(View v){
        if (v == mSavedRecipesButton){
            Intent intent = new Intent(MainActivity.this, SavedRecipesListActivity.class);
            startActivity(intent);
        }
        if (v == mFindFoodButton){
            String food = mFoodEditText.getText().toString();
            if (!(food).equals("")){
                addToSharedPreferences(food);
            }

            Intent intent = new Intent(MainActivity.this, RecipeListActivity.class);
            startActivity(intent);
        }
    }

    private void addToSharedPreferences(String food){
        mEditor.putString(Constants.PREFERENCES_FOOD_KEY, food).apply();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.nav_recipies) {
            Intent recipeListIntent =new Intent(MainActivity.this, RecipeListActivity.class);
            startActivity(recipeListIntent);

        } else if (id == R.id.nav_about) {
            Intent aboutIntent = new Intent(MainActivity.this, AboutActivity.class);
            startActivity(aboutIntent);

        } else if (id == R.id.nav_contact) {
            Intent contactIntent = new Intent(MainActivity.this, ContactActivity.class);
            startActivity(contactIntent);

        } else if (id == R.id.nav_add_recipe) {
            Intent newRecipeIntent = new Intent(MainActivity.this, NewRecipeActivity.class);
            startActivity(newRecipeIntent);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
