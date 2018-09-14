package com.iamagamedev.sharpdeveloperstestjava.ui.transactionsHistoryActivity;

import android.content.Intent;
import android.os.Bundle;

import com.iamagamedev.sharpdeveloperstestjava.app.Constants;
import com.iamagamedev.sharpdeveloperstestjava.app.ThisApplication;
import com.iamagamedev.sharpdeveloperstestjava.repository.Repository;
import com.iamagamedev.sharpdeveloperstestjava.repository.models.TransactionObject;
import com.iamagamedev.sharpdeveloperstestjava.ui.profileActivity.ProfileActivity;
import com.iamagamedev.sharpdeveloperstestjava.utils.Utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class TransactionHistoryInteractor implements ITransactionHistoryInteractor {

    @Override
    public void getTransactionList(OnTransactionHistoryListener listener) {
        Repository.getTransactionList(Utils.getToken(), listener);
    }

    @Override
    public void verifyRequest(TransactionObject transactionObject, OnTransactionHistoryListener listener) {
        if (Integer.parseInt(transactionObject.getAmount()) > 0) {
            listener.onError("Can'n use receive transactions as basis");
        } else {
            Intent intent = new Intent(ThisApplication.getInstance(), ProfileActivity.class);
            Bundle bundle = new Bundle();
            bundle.putParcelable(Constants.SAVE_TRANSACTION, transactionObject);
            intent.putExtras(bundle);
            listener.onVerificationSuccess(intent);
        }
    }

    @Override
    public void sortList(List<TransactionObject> transactionObjects, String filter, OnTransactionHistoryListener listener) {
        switch (filter) {
            case Constants.SORT_BY_NAME:
                sortListByName(transactionObjects);
                break;
            case Constants.SORT_BY_DATE:
                sortListByDate(transactionObjects);
                break;
            case Constants.SORT_BY_AMOUNT:
                sortListByAmount(transactionObjects);
                break;
            case Constants.SORT_NOT:
                break;
        }
        listener.onSortFinished(transactionObjects);
    }


    private class EventDetailSortByDate implements Comparator<TransactionObject> {
        @Override
        public int compare(TransactionObject customerEvents1, TransactionObject customerEvents2) {
            String name1, name2;
            name1 = customerEvents1.getDate().toLowerCase().trim();
            name2 = customerEvents2.getDate().toLowerCase().trim();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
            long time1 = 0;
            long time2 = 0;
            try {
                Date mDate = sdf.parse(name1);
                Date date = sdf.parse(name2);
                time1 = mDate.getTime();
                time2 = date.getTime();

            } catch (ParseException e) {
                e.printStackTrace();
            }
            return String.valueOf(time1).compareTo(String.valueOf(time2));
        }
    }

    private class EventDetailSortByName implements Comparator<TransactionObject> {
        @Override
        public int compare(TransactionObject customerEvents1, TransactionObject customerEvents2) {
            String name1, name2;
            name1 = customerEvents1.getUsername().toLowerCase().trim();
            name2 = customerEvents2.getUsername().toLowerCase().trim();
            return name1.compareTo(name2);
        }
    }

    private class EventDetailSortByAmount implements Comparator<TransactionObject> {
        @Override
        public int compare(TransactionObject customerEvents1, TransactionObject customerEvents2) {
            String name1, name2;
            name1 = customerEvents1.getAmount().toLowerCase().trim();
            name2 = customerEvents2.getAmount().toLowerCase().trim();
            return name1.compareTo(name2);
        }
    }

    private void sortListByName(List<TransactionObject> theArrayListEvents) {
        Collections.sort(theArrayListEvents, new EventDetailSortByName());
    }

    private void sortListByDate(List<TransactionObject> theArrayListEvents) {
        Collections.sort(theArrayListEvents, new EventDetailSortByDate());
    }

    private void sortListByAmount(List<TransactionObject> theArrayListEvents) {
        Collections.sort(theArrayListEvents, new EventDetailSortByAmount());
    }
}
