package com.iamagamedev.sharpdeveloperstestjava.repository.repClasses;

import com.iamagamedev.sharpdeveloperstestjava.R;
import com.iamagamedev.sharpdeveloperstestjava.app.ThisApplication;
import com.iamagamedev.sharpdeveloperstestjava.repository.models.TokenObject;
import com.iamagamedev.sharpdeveloperstestjava.repository.server.ServerMethods;
import com.iamagamedev.sharpdeveloperstestjava.ui.loginActivity.ILoginInteractor;
import com.iamagamedev.sharpdeveloperstestjava.ui.registerActivity.IRegisterInteractor;
import com.iamagamedev.sharpdeveloperstestjava.utils.OnlineChecker;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RepositoryLoginRegister {

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
}
