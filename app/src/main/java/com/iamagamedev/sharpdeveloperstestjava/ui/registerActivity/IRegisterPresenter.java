package com.iamagamedev.sharpdeveloperstestjava.ui.registerActivity;

import com.iamagamedev.sharpdeveloperstestjava.ui.generalActivity.IGeneralPresenter;

public interface IRegisterPresenter extends IGeneralPresenter {

    void onAttachView(IRegisterView view);

    void register(String username, String password, String password2,
                 String email);
}
