package com.iamagamedev.sharpdeveloperstestjava.utils;

import android.os.Handler;
import android.support.design.widget.TextInputLayout;

import com.iamagamedev.sharpdeveloperstestjava.app.Constants;
import com.iamagamedev.sharpdeveloperstestjava.app.SharedPreferencesClass;


public class Utils {

    private static final long ERROR_SHOW_TIMEOUT = 4000L;

    public static void showValidationError(final TextInputLayout layout, String error) {
        layout.setErrorEnabled(true);
        layout.setError(error);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                layout.setErrorEnabled(false);
                layout.setError("");
            }
        }, ERROR_SHOW_TIMEOUT);
    }

    public static String getToken() {
        return "Bearer " + SharedPreferencesClass.getStringFromPreferences(Constants.SAVE_TOKEN);
    }
}
