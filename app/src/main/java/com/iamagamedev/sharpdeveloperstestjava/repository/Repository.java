package com.iamagamedev.sharpdeveloperstestjava.repository;

import com.iamagamedev.sharpdeveloperstestjava.R;
import com.iamagamedev.sharpdeveloperstestjava.app.ThisApplication;
import com.iamagamedev.sharpdeveloperstestjava.repository.models.TokenObject;
import com.iamagamedev.sharpdeveloperstestjava.repository.models.TransactionListObject;
import com.iamagamedev.sharpdeveloperstestjava.repository.models.TransactionObject;
import com.iamagamedev.sharpdeveloperstestjava.repository.models.UserInfoToken;
import com.iamagamedev.sharpdeveloperstestjava.repository.models.UserListObject;
import com.iamagamedev.sharpdeveloperstestjava.repository.server.ServerMethods;
import com.iamagamedev.sharpdeveloperstestjava.ui.loginActivity.ILoginInteractor;
import com.iamagamedev.sharpdeveloperstestjava.ui.profileActivity.IProfileInteractor;
import com.iamagamedev.sharpdeveloperstestjava.ui.registerActivity.IRegisterInteractor;
import com.iamagamedev.sharpdeveloperstestjava.ui.transactionsHistoryActivity.ITransactionHistoryInteractor;
import com.iamagamedev.sharpdeveloperstestjava.utils.OnlineChecker;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Repository {

    public static void registerUser(String username, String password, String email,
                                    final IRegisterInteractor.OnRegisterListener listener) {
        if (OnlineChecker.isOnline()) {
            ServerMethods.registerUser(username, password, email, new Callback<TokenObject>() {
                @Override
                public void onFailure(Call<TokenObject> call, Throwable t) {
                    listener.onError(t.getLocalizedMessage());
                }

                @Override
                public void onResponse(Call<TokenObject> call, Response<TokenObject> response) {
                    if (response.body() == null) {
                        listener.onError(response.message(), response.code());
                    } else {
                        listener.onSuccess();
                    }
                }
            });
        } else {
            listener.onError(ThisApplication.getInstance().getString(R.string.no_internet));
        }
    }

    public static void loginUser(String email, String password,
                                 final ILoginInteractor.OnLoginListener listener) {
        if (OnlineChecker.isOnline()) {
            ServerMethods.loginUser(email, password, new Callback<TokenObject>() {
                @Override
                public void onResponse(Call<TokenObject> call, Response<TokenObject> response) {
                    if (response.body() != null)
                        listener.onSuccess(response.body().getToken());
                    else
                        listener.onError(response.message(), response.code());
                }

                @Override
                public void onFailure(Call<TokenObject> call, Throwable t) {
                    listener.onError(t.getLocalizedMessage());
                }
            });
        } else {
            listener.onError(ThisApplication.getInstance().getString(R.string.no_internet));
        }
    }

    public static void getUserInfo(String token, final IProfileInteractor.OnProfileListener listener) {
        if (OnlineChecker.isOnline()) {
            ServerMethods.getUserInfo(token, new Callback<UserInfoToken>() {
                @Override
                public void onResponse(Call<UserInfoToken> call, Response<UserInfoToken> response) {
                    if (response.body() != null) {
                        listener.onSuccessUserInfo(response.body().getUserObject());
                    } else {
                        listener.onError(response.message(), response.code());
                    }
                }

                @Override
                public void onFailure(Call<UserInfoToken> call, Throwable t) {
                    listener.onError(t.getLocalizedMessage());
                }
            });
        } else {
            listener.onError(ThisApplication.getInstance().getString(R.string.no_internet));
        }
    }

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

    public static void getUsersList(String token, String filter,
                                    final IProfileInteractor.OnProfileListener listener) {
        if (OnlineChecker.isOnline()) {
            ServerMethods.getUsersList(token, filter, new Callback<List<UserListObject>>() {
                @Override
                public void onResponse(Call<List<UserListObject>> call, Response<List<UserListObject>> response) {
                    if (response.body() != null) {
                        listener.onSuccessUsersList(new ArrayList<UserListObject>(response.body()));
                    } else {
                        listener.onError(response.message(), response.code());
                    }
                }

                @Override
                public void onFailure(Call<List<UserListObject>> call, Throwable t) {
                    listener.onError(t.getLocalizedMessage());
                }
            });
        } else {
            listener.onError(ThisApplication.getInstance().getString(R.string.no_internet));
        }
    }
}
