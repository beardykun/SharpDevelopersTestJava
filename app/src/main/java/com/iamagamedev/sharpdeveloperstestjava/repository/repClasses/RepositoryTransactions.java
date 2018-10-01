package com.iamagamedev.sharpdeveloperstestjava.repository.repClasses;

import com.iamagamedev.sharpdeveloperstestjava.R;
import com.iamagamedev.sharpdeveloperstestjava.app.ThisApplication;
import com.iamagamedev.sharpdeveloperstestjava.repository.models.TransactionListObject;
import com.iamagamedev.sharpdeveloperstestjava.repository.models.TransactionObject;
import com.iamagamedev.sharpdeveloperstestjava.repository.server.ServerMethods;
import com.iamagamedev.sharpdeveloperstestjava.ui.profileActivity.IProfileInteractor;
import com.iamagamedev.sharpdeveloperstestjava.ui.transactionsHistoryActivity.ITransactionHistoryInteractor;
import com.iamagamedev.sharpdeveloperstestjava.utils.OnlineChecker;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RepositoryTransactions {

    public static void getTransactionList(String token,
                                          final ITransactionHistoryInteractor.OnTransactionHistoryListener listener) {
        if (OnlineChecker.isOnline()) {
            ServerMethods.getTransactionList(token, new Callback<TransactionListObject>() {
                @Override
                public void onResponse(Call<TransactionListObject> call,
                                       Response<TransactionListObject> response) {
                    if (response.body() != null) {
                        listener.onSuccess(response.body());
                    } else {
                        listener.onError(response.message(), response.code());
                    }
                }

                @Override
                public void onFailure(Call<TransactionListObject> call, Throwable t) {
                    listener.onError(t.getLocalizedMessage());
                }
            });
        } else {
            listener.onError(ThisApplication.getInstance().getString(R.string.no_internet));
        }
    }

    public static void createTransaction(String token, String name, String amount,
                                         final IProfileInteractor.OnProfileListener listener) {
        if (OnlineChecker.isOnline()) {
            ServerMethods.createTransaction(token, name, amount, new Callback<TransactionObject>() {
                @Override
                public void onResponse(Call<TransactionObject> call, Response<TransactionObject> response) {
                    if (response.body() != null) {
                        listener.onSuccessCreateTransaction(response.body());
                    } else {
                        listener.onCreateTransactionError(response.message(), response.code());
                    }
                }

                @Override
                public void onFailure(Call<TransactionObject> call, Throwable t) {
                    listener.onError(t.getLocalizedMessage());
                }
            });
        } else {
            listener.onError(ThisApplication.getInstance().getString(R.string.no_internet));
        }
    }

}
