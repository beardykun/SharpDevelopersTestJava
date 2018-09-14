package com.iamagamedev.sharpdeveloperstestjava.ui.registerActivity;

import com.iamagamedev.sharpdeveloperstestjava.ui.generalActivity.IGeneralInteractorListener;

public interface IRegisterInteractor {

    interface OnRegisterListener extends IGeneralInteractorListener {
        void onUsernameValidationFailed();

        void onPasswordValidationFailed();

        void onPassword2ValidationFailed();

        void onEmailValidationFailed();

        void onSuccess();
    }

    void registerUser(String username, String password, String password2,
                      String email, OnRegisterListener listener);
}
