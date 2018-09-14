package com.iamagamedev.sharpdeveloperstestjava.repository.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TransactionListObject {

    @SerializedName("trans_token")
    @Expose
    private List<TransactionObject>transactionObjects;

    public List<TransactionObject> getTransactionObjects() {
        return transactionObjects;
    }

    public void setTransactionObjects(List<TransactionObject> transactionObjects) {
        this.transactionObjects = transactionObjects;
    }
}
