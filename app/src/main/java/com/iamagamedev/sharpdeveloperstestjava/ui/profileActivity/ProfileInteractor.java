package com.iamagamedev.sharpdeveloperstestjava.ui.profileActivity;

import com.iamagamedev.sharpdeveloperstestjava.utils.Utils;
import com.iamagamedev.sharpdeveloperstestjava.repository.Repository;

public class ProfileInteractor implements IProfileInteractor {
    @Override
    public void getUserInfo(OnProfileListener listener) {
        Repository.getUserInfo(Utils.getToken(), listener);
    }

    @Override
    public void createTransaction(String name, String amount, String balance, OnProfileListener listener) {
        if (validate(name, amount, balance, listener))
            Repository.createTransaction(Utils.getToken(), name, amount, listener);
    }

    @Override
    public void getUsersList(String filter, OnProfileListener listener) {
        Repository.getUsersList(Utils.getToken(), filter, listener);
    }

    private boolean validate(String name, String amount, String balance, OnProfileListener listener) {

        if (name.length() < 3) {
            listener.onRecipientValidationFailed();
            return false;
        } else if (amount.isEmpty()) {
            listener.onAmountValidationFailed();
            return false;
        } else if (Integer.parseInt(amount) > Integer.parseInt(balance)) {
            listener.amountMoreThanBalance();
            return false;
        }
        return true;
    }
}
