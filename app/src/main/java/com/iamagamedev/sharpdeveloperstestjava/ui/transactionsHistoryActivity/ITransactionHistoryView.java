package com.iamagamedev.sharpdeveloperstestjava.ui.transactionsHistoryActivity;

import android.content.Intent;

import com.iamagamedev.sharpdeveloperstestjava.repository.models.TransactionObject;
import com.iamagamedev.sharpdeveloperstestjava.ui.generalActivity.IGeneralView;

import java.util.List;

public interface ITransactionHistoryView extends IGeneralView {

    void populateAdapter(List<TransactionObject> transactionObjects);

    void backToProfile(Intent intent);

    void sortList(List<TransactionObject> transactionObjects);

    void finishActivity();
}
