package com.example.epicodus.recipeblog.services;

import android.util.Log;

import com.example.epicodus.recipeblog.Constants;
import com.example.epicodus.recipeblog.models.Recipe;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class YummlyService {
    public static void findRecipes(String food, Callback callback){
        OkHttpClient client = new OkHttpClient.Builder()
                .build();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.YUMMLY_BASE_URL).newBuilder();
        urlBuilder.addQueryParameter(Constants.API_ID_PARAMETER, Constants.API_ID);
        urlBuilder.addQueryParameter(Constants.API_KEY_PARAMETER, Constants.API_KEY);
        urlBuilder.addQueryParameter(Constants.YUMMLY_FOOD_QUERY_PARAMETER, food);

        String url = urlBuilder.build().toString();

        Request request = new Request.Builder()
                .url(url)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);
    }
    public ArrayList<Recipe> processResults(Response response){
        ArrayList<Recipe> recipes = new ArrayList<>();
        try{
            String jsonData = response.body().string();

            JSONObject yummlyJSON = new JSONObject(jsonData);
            JSONArray resultsJSON = yummlyJSON.getJSONArray("matches");

            for (int i=0; i < resultsJSON.length(); i++){
                JSONObject recipeJSON = resultsJSON.getJSONObject(i);

                String recipeName = recipeJSON.getString("recipeName");
                int totalTime = recipeJSON.getInt("totalTimeInSeconds");

                int rating = recipeJSON.getInt("rating");

                String imageUrl;
                JSONArray imageJSON = recipeJSON.getJSONArray("smallImageUrls");
                imageUrl =imageJSON.get(0).toString();

                ArrayList<String> ingredients = new ArrayList<>();
                JSONArray ingredientJSON = recipeJSON.getJSONArray("ingredients");
                for (int j = 0; j < ingredientJSON.length(); j++) {
                    ingredients.add(ingredientJSON.get(j).toString());
                }

                Recipe recipe = new Recipe(recipeName, totalTime, ingredients, rating, imageUrl);
                recipes.add(recipe);
            }

        }catch (IOException e){
            e.printStackTrace();
        }catch (JSONException e){
            e.printStackTrace();
        }
        return recipes;
    }
}
