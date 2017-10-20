package com.example.epicodus.recipeblog;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;

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
}
