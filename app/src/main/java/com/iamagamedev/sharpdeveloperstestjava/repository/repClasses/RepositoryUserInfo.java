package com.iamagamedev.sharpdeveloperstestjava.repository.repClasses;

import com.iamagamedev.sharpdeveloperstestjava.R;
import com.iamagamedev.sharpdeveloperstestjava.app.ThisApplication;
import com.iamagamedev.sharpdeveloperstestjava.repository.models.UserInfoToken;
import com.iamagamedev.sharpdeveloperstestjava.repository.models.UserListObject;
import com.iamagamedev.sharpdeveloperstestjava.repository.server.ServerMethods;
import com.iamagamedev.sharpdeveloperstestjava.ui.profileActivity.IProfileInteractor;
import com.iamagamedev.sharpdeveloperstestjava.utils.OnlineChecker;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RepositoryUserInfo {


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
