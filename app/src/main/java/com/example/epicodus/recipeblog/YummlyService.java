package com.example.epicodus.recipeblog;

import android.util.Log;

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

/**
 * Created by Guest on 10/20/17.
 */

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
            JSONArray resultsJSON = yummlyJSON.getJSONArray("results");

            for (int i=0; i < resultsJSON.length(); i++){
                JSONObject recipeJSON = resultsJSON.getJSONObject(i);

                String recipeName = recipeJSON.getString("recipeName");
                int totalTime = recipeJSON.getInt("totalTimeInSeconds");
                String ingredientsString = recipeJSON.getString("ingredients");
                int rating = recipeJSON.getInt("rating");

                String [] ingredients = ingredientsString.split(",");

                Recipe recipe = new Recipe(recipeName, totalTime, ingredients, rating);
                recipes.add(recipe);
            }

        }catch (IOException e){
            e.printStackTrace();
        }catch (JSONException e){
            e.printStackTrace();
            Log.d("INSIDE JSON ", "test" +e);
        }
        return recipes;
    }
}
