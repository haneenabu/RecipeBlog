package com.example.epicodus.recipeblog;


public class Constants {
    public static final String API_KEY = BuildConfig.API_KEY;
    public static final String API_ID = BuildConfig.API_ID;
    public static final String YUMMLY_BASE_URL = "http://api.yummly.com/v1/api/recipes?allowedDiet[]=390^Paleo";
    public static final String YUMMLY_FOOD_QUERY_PARAMETER = "q";
    public static final String API_ID_PARAMETER ="_app_id";
    public static final String API_KEY_PARAMETER ="_app_key";

    public static final String FIREBASE_CHILD_RECIPE = "recipes";
    public static final String PREFERENCES_FOOD_KEY = "food";
    public static final String FIREBASE_QUERY_INDEX = "index";

    public static final String EXTRA_KEY_POSITION = "position";
    public static final String EXTRA_KEY_RECIPES = "recipes";
}
