package com.iamagamedev.sharpdeveloperstestjava.utils;

import android.content.Context;
import android.net.ConnectivityManager;

import com.iamagamedev.sharpdeveloperstestjava.app.ThisApplication;

public class OnlineChecker {

    public static boolean isOnline() {
        try {
            ConnectivityManager cm = (ConnectivityManager) ThisApplication.getInstance().getSystemService(Context.CONNECTIVITY_SERVICE);
            return cm.getActiveNetworkInfo().isConnectedOrConnecting();
        } catch (Exception e) {
            return false;
        }
    }
}
