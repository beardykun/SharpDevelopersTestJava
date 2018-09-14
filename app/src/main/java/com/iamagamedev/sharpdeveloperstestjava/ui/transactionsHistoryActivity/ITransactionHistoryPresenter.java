package com.iamagamedev.sharpdeveloperstestjava.ui.transactionsHistoryActivity;

import com.iamagamedev.sharpdeveloperstestjava.repository.models.TransactionObject;
import com.iamagamedev.sharpdeveloperstestjava.ui.generalActivity.IGeneralPresenter;

import java.util.List;

public interface ITransactionHistoryPresenter extends IGeneralPresenter {

    void onAttachView(ITransactionHistoryView view);

    void getTransactionList();

    void saveObject(TransactionObject object);

    void sortList(List<TransactionObject> transactionObjects, String filter);
}
