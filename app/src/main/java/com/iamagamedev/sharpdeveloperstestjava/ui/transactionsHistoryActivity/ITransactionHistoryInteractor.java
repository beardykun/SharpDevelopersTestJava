package com.iamagamedev.sharpdeveloperstestjava.ui.transactionsHistoryActivity;

import android.content.Intent;

import com.iamagamedev.sharpdeveloperstestjava.repository.models.TransactionListObject;
import com.iamagamedev.sharpdeveloperstestjava.repository.models.TransactionObject;
import com.iamagamedev.sharpdeveloperstestjava.ui.generalActivity.IGeneralInteractorListener;

import java.util.List;

public interface ITransactionHistoryInteractor {

    interface OnTransactionHistoryListener extends IGeneralInteractorListener {
        void onSuccess(TransactionListObject transactionObjects);

        void onVerificationSuccess(Intent intent);

        void onSortFinished(List<TransactionObject> transactionObjects);
    }

    void getTransactionList(OnTransactionHistoryListener listener);

    void verifyRequest(TransactionObject transactionObject, OnTransactionHistoryListener listener);

    void sortList(List<TransactionObject> transactionObjects, String filter,
                  OnTransactionHistoryListener listener);
}
