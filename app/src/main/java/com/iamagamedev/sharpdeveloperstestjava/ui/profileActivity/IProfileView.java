package com.iamagamedev.sharpdeveloperstestjava.ui.profileActivity;

import com.iamagamedev.sharpdeveloperstestjava.repository.models.UserListObject;
import com.iamagamedev.sharpdeveloperstestjava.ui.generalActivity.IGeneralView;

import java.util.ArrayList;

public interface IProfileView extends IGeneralView {

    void populateViews(String id, String name, String email, String balance);

    void callUserListDialog(ArrayList<UserListObject> userListObjects);

    void setTransactionData(String name, String amount);

    void onRecipientValidationFailed();

    void onAmountValidationFailed();

    void amountMoreThanBalance();

    void transactionCompleted();
}
