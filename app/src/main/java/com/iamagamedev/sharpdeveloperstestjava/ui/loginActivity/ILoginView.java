package com.iamagamedev.sharpdeveloperstestjava.ui.loginActivity;

import com.iamagamedev.sharpdeveloperstestjava.ui.generalActivity.IGeneralView;

public interface ILoginView extends IGeneralView {

    void showPasswordValidationError();

    void showEmailValidationError();

    void goToProfile();
}
