package com.iamagamedev.sharpdeveloperstestjava.repository.server;

import com.iamagamedev.sharpdeveloperstestjava.repository.models.TokenObject;
import com.iamagamedev.sharpdeveloperstestjava.repository.models.TransactionListObject;
import com.iamagamedev.sharpdeveloperstestjava.repository.models.TransactionObject;
import com.iamagamedev.sharpdeveloperstestjava.repository.models.UserInfoToken;
import com.iamagamedev.sharpdeveloperstestjava.repository.models.UserListObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class ServerMethods {

    public static void registerUser(String username, String password, String email, Callback<TokenObject> cb) {
        Call<TokenObject> call = RetrofitForMyApp.getRetrofitService().registerUser(username, password, email);
        call.enqueue(cb);
    }

    public static void loginUser(String email, String password, Callback<TokenObject> cb) {
        Call<TokenObject> call = RetrofitForMyApp.getRetrofitService().loginUser(email, password);
        call.enqueue(cb);
    }

    public static void getUserInfo(String token, Callback<UserInfoToken> cb) {
        Call<UserInfoToken> call = RetrofitForMyApp.getRetrofitService().getUserInfo(token);
        call.enqueue(cb);
    }

    public static void getTransactionList(String token, Callback<TransactionListObject> cb) {
        Call<TransactionListObject> call = RetrofitForMyApp.getRetrofitService()
                .getTransactionsList(token);
        call.enqueue(cb);
    }

    public static void createTransaction(String token, String name, String amount,
                                         Callback<TransactionObject> cb) {
        Call<TransactionObject> call = RetrofitForMyApp.getRetrofitService()
                .createTransaction(token, name, amount);
        call.enqueue(cb);
    }

    public static void getUsersList(String token, String filter, Callback<List<UserListObject>> cb) {
        Call<List<UserListObject>> call = RetrofitForMyApp.getRetrofitService()
                .getUsersList(token, filter);
        call.enqueue(cb);
    }
}
