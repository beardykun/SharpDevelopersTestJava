package com.iamagamedev.sharpdeveloperstestjava.repository.server;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitForMyApp {

private static Retrofit retrofit;
private static Gson gson;

private static AppApi service;

private RetrofitForMyApp(){ }

    public static void init(){
    gson = new GsonBuilder().create();

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient logger = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        retrofit = new Retrofit.Builder()
                .baseUrl(Urls.BASE_URL)
                .client(logger)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        service = retrofit.create(AppApi.class);
    }

    public static AppApi getRetrofitService() {
    if (service != null){
        return service;
    }else
        throw new IllegalStateException("retrofit not initialized");
    }
}
