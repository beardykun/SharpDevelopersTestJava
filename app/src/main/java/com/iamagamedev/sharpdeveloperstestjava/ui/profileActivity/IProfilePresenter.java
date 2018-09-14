package com.iamagamedev.sharpdeveloperstestjava.ui.profileActivity;

import android.os.Bundle;

import com.iamagamedev.sharpdeveloperstestjava.ui.generalActivity.IGeneralPresenter;

public interface IProfilePresenter extends IGeneralPresenter {

    void onAttachView(IProfileView view);

    void getUserInfo();

    void createTransaction(String name, String amount, String balance);

    void getUsersList(String filter);

    void setTransactionData(Bundle bundle);
}
