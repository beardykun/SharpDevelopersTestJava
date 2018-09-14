package com.iamagamedev.sharpdeveloperstestjava.ui.profileActivity;

import com.iamagamedev.sharpdeveloperstestjava.repository.models.TransactionObject;
import com.iamagamedev.sharpdeveloperstestjava.repository.models.UserListObject;
import com.iamagamedev.sharpdeveloperstestjava.repository.models.UserObject;
import com.iamagamedev.sharpdeveloperstestjava.ui.generalActivity.IGeneralInteractorListener;

import java.util.ArrayList;

public interface IProfileInteractor {

    interface OnProfileListener extends IGeneralInteractorListener {
        void onSuccessUserInfo(UserObject userObject);

        void onSuccessCreateTransaction(TransactionObject transactionObject);

        void onSuccessUsersList(ArrayList<UserListObject> userListObjects);

        void onCreateTransactionError(String error, int... code);

        void onRecipientValidationFailed();

        void onAmountValidationFailed();

        void amountMoreThanBalance();
    }

    void getUserInfo(OnProfileListener listener);

    void createTransaction(String name, String amount, String balance, OnProfileListener listener);

    void getUsersList(String filter, OnProfileListener listener);
}
