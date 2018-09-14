package com.iamagamedev.sharpdeveloperstestjava.ui.registerActivity;

import com.iamagamedev.sharpdeveloperstestjava.ui.generalActivity.IGeneralView;

public interface IRegisterView extends IGeneralView {

    void showUsernameValidationError();

    void showPasswordValidationError();

    void showPassword2ValidationError();

    void showEmailValidationError();

    void onRegistrationCompleted();
}
