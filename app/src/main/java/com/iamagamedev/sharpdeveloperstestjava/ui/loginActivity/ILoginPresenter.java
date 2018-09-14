package com.iamagamedev.sharpdeveloperstestjava.ui.loginActivity;

import com.iamagamedev.sharpdeveloperstestjava.ui.generalActivity.IGeneralPresenter;

public interface ILoginPresenter extends IGeneralPresenter {

    void onAttachView(ILoginView view);

    void loginUser(String email, String password);
}
