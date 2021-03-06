package com.iamagamedev.sharpdeveloperstestjava.ui.profileActivity;

import com.iamagamedev.sharpdeveloperstestjava.R;
import com.iamagamedev.sharpdeveloperstestjava.app.Constants;
import com.iamagamedev.sharpdeveloperstestjava.app.SharedPreferencesClass;
import com.iamagamedev.sharpdeveloperstestjava.app.ThisApplication;
import com.iamagamedev.sharpdeveloperstestjava.repository.repClasses.RepositoryUserInfo;
import com.iamagamedev.sharpdeveloperstestjava.repository.repClasses.RepositoryTransactions;
import com.iamagamedev.sharpdeveloperstestjava.utils.Utils;

public class ProfileInteractor implements IProfileInteractor {
    @Override
    public void getUserInfo(OnProfileListener listener) {
        RepositoryUserInfo.getUserInfo(Utils.getToken(), listener);
    }

    @Override
    public void createTransaction(String name, String amount, String balance, OnProfileListener listener) {
        if (validate(name, amount, balance, listener))
            RepositoryTransactions.createTransaction(Utils.getToken(), name, amount, listener);
    }

    @Override
    public void getUsersList(String filter, OnProfileListener listener) {
        RepositoryUserInfo.getUsersList(Utils.getToken(), filter, listener);
    }

    private boolean validate(String name, String amount, String balance, OnProfileListener listener) {

        if (name.length() < 3) {
            listener.onRecipientValidationFailed();
            return false;
        } else if (amount.isEmpty() || Integer.parseInt(amount) == 0) {
            listener.onAmountValidationFailed();
            return false;
        } else if (Integer.parseInt(amount) > Integer.parseInt(balance)) {
            listener.amountMoreThanBalance();
            return false;
        } else if (name.equals(SharedPreferencesClass.getStringFromPreferences(Constants.SAVE_USERNAME))) {
            listener.onError(ThisApplication.getInstance().getString(R.string.self_transfer));
            return false;
        }
        return true;
    }
}
