package com.iamagamedev.sharpdeveloperstestjava.ui.profileActivity;

import android.os.Bundle;

import com.iamagamedev.sharpdeveloperstestjava.app.Constants;
import com.iamagamedev.sharpdeveloperstestjava.repository.models.TransactionObject;
import com.iamagamedev.sharpdeveloperstestjava.repository.models.UserListObject;
import com.iamagamedev.sharpdeveloperstestjava.repository.models.UserObject;

import java.util.ArrayList;

public class ProfilePresenter implements IProfilePresenter, IProfileInteractor.OnProfileListener {

    private IProfileView view;
    private IProfileInteractor interactor;

    ProfilePresenter() {
        interactor = new ProfileInteractor();
    }

    @Override
    public void onAttachView(IProfileView view) {
        this.view = view;
    }

    @Override
    public void getUserInfo() {
        if (view != null) {
            view.showProgress();
            interactor.getUserInfo(this);
        }
    }

    @Override
    public void createTransaction(String name, String amount, String balance) {
        if (view != null) {
            view.showProgress();
            interactor.createTransaction(name, amount, balance, this);
        }
    }

    @Override
    public void getUsersList(String filter) {
        if (view != null) {
            view.showProgress();
            interactor.getUsersList(filter,this);
        }
    }

    @Override
    public void setTransactionData(Bundle bundle) {
        if (view != null) {
            view.showProgress();
            TransactionObject object = bundle.getParcelable(Constants.SAVE_TRANSACTION);
            view.setTransactionData(object.getUsername(), object.getAmount());
            view.hideProgress();
        }
    }

    @Override
    public void onDetachView() {
        this.view = null;
    }

    @Override
    public void onSuccessUserInfo(UserObject userObject) {
        if (view != null) {
            view.hideProgress();
            view.populateViews(userObject.getId(), userObject.getName(),
                    userObject.getEmail(), userObject.getBalance());
        }
    }

    @Override
    public void onSuccessCreateTransaction(TransactionObject transactionObject) {
        if (view != null) {
            view.hideProgress();
            interactor.getUserInfo(this);
            view.transactionCompleted();
        }
    }

    @Override
    public void onSuccessUsersList(ArrayList<UserListObject> userListObjects) {
        if (view != null) {
            view.hideProgress();
            view.callUserListDialog(userListObjects);
        }
    }

    @Override
    public void onError(String error, int... code) {
        if (view != null) {
            view.hideProgress();
            view.showError(error, code);
        }
    }

    @Override
    public void onCreateTransactionError(String error, int... code) {
        if (view != null) {
            view.hideProgress();
            if (code[0] == 400) {
                view.showError("User not found", 400);
            } else if (code[0] == 401) {
                view.showError("Invalid user", 401);
            }
        }
    }

    @Override
    public void onRecipientValidationFailed() {
        if (view != null) {
            view.hideProgress();
            view.onRecipientValidationFailed();
        }
    }

    @Override
    public void onAmountValidationFailed() {
        if (view != null) {
            view.hideProgress();
            view.onAmountValidationFailed();
        }
    }

    @Override
    public void amountMoreThanBalance() {
        if (view != null) {
            view.hideProgress();
            view.amountMoreThanBalance();
        }
    }
}
