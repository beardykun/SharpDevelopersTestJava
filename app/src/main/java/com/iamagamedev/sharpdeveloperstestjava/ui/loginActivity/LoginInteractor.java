package com.iamagamedev.sharpdeveloperstestjava.ui.loginActivity;

import com.iamagamedev.sharpdeveloperstestjava.app.Constants;
import com.iamagamedev.sharpdeveloperstestjava.app.SharedPreferencesClass;
import com.iamagamedev.sharpdeveloperstestjava.repository.repClasses.RepositoryLoginRegister;

public class LoginInteractor implements ILoginInteractor {

    @Override
    public void loginUser(String email, String password, OnLoginListener listener) {
        if (validate(email, password, listener)) {
            RepositoryLoginRegister.loginUser(email, password, listener);
        }
    }

    private boolean validate(String email, String password, ILoginInteractor.OnLoginListener listener) {
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            listener.onEmailValidationFailed();
            return false;
        } else if (password.length() < 6) {
            listener.onPasswordValidationFailed();
            return false;
        }
        return true;
    }

    @Override
    public void saveToken(String token) {
        SharedPreferencesClass.saveStringInPreferences(Constants.SAVE_TOKEN, token);
    }
}
