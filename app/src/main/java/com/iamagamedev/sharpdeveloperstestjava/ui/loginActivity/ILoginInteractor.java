package com.iamagamedev.sharpdeveloperstestjava.ui.loginActivity;

import com.iamagamedev.sharpdeveloperstestjava.ui.generalActivity.IGeneralInteractorListener;

public interface ILoginInteractor {

    interface OnLoginListener extends IGeneralInteractorListener {

        void onSuccess(String token);

        void onEmailValidationFailed();

        void onPasswordValidationFailed();
    }

    void loginUser(String email, String password, OnLoginListener listener);

    void saveToken(String token);
}
