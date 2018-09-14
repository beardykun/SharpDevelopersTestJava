package com.iamagamedev.sharpdeveloperstestjava.app;

import android.app.Application;

import com.iamagamedev.sharpdeveloperstestjava.repository.server.RetrofitForMyApp;

public class ThisApplication extends Application {
    private static ThisApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        initRetrofit();

    }

    public static ThisApplication getInstance() {
        return instance;
    }


    private void initRetrofit() {
        RetrofitForMyApp.init();
    }

}
