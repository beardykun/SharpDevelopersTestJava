package com.iamagamedev.sharpdeveloperstestjava.ui.transactionsHistoryActivity;

import android.content.Intent;

import com.iamagamedev.sharpdeveloperstestjava.repository.models.TransactionListObject;
import com.iamagamedev.sharpdeveloperstestjava.repository.models.TransactionObject;

import java.util.List;

public class TransactionHistoryPresenter implements ITransactionHistoryPresenter,
        ITransactionHistoryInteractor.OnTransactionHistoryListener {

    private ITransactionHistoryView view;
    private ITransactionHistoryInteractor interactor;

    public TransactionHistoryPresenter() {
        interactor = new TransactionHistoryInteractor();
    }

    @Override
    public void onAttachView(ITransactionHistoryView view) {
        this.view = view;
    }

    @Override
    public void getTransactionList() {
        if (view != null) {
            view.showProgress();
            interactor.getTransactionList(this);
        }
    }

    @Override
    public void saveObject(TransactionObject object) {
        if (view != null) {
            view.showProgress();
            interactor.verifyRequest(object, this);
        }
    }

    @Override
    public void sortList(List<TransactionObject> transactionObjects, String filter) {
        if (view != null) {
            view.showProgress();
            interactor.sortList(transactionObjects, filter, this);
        }
    }

    @Override
    public void onDetachView() {
        this.view = null;
    }

    @Override
    public void onSuccess(TransactionListObject transactionObjects) {
        if (view != null) {
            view.hideProgress();
            if (transactionObjects.getTransactionObjects().isEmpty()){
                view.finishActivity();
            }else {
                view.sortList(transactionObjects.getTransactionObjects());
            }
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
    public void onVerificationSuccess(Intent intent) {
        if (view != null) {
            view.hideProgress();
            view.backToProfile(intent);
        }
    }

    @Override
    public void onSortFinished(List<TransactionObject> transactionObjects) {
        if (view != null) {
            view.hideProgress();
            view.populateAdapter(transactionObjects);
        }
    }
}
