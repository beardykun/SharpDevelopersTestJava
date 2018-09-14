package com.iamagamedev.sharpdeveloperstestjava.repository.server;

import com.iamagamedev.sharpdeveloperstestjava.repository.models.TokenObject;
import com.iamagamedev.sharpdeveloperstestjava.repository.models.TransactionListObject;
import com.iamagamedev.sharpdeveloperstestjava.repository.models.TransactionObject;
import com.iamagamedev.sharpdeveloperstestjava.repository.models.UserInfoToken;
import com.iamagamedev.sharpdeveloperstestjava.repository.models.UserListObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface AppApi {

    @FormUrlEncoded
    @POST(Urls.REGISTER_URL)
    Call<TokenObject> registerUser(@Field("username") String username,
                                   @Field("password") String password,
                                   @Field("email") String email);

    @FormUrlEncoded
    @POST(Urls.LOGIN_URL)
    Call<TokenObject> loginUser(@Field("email") String email,
                                @Field("password") String password);

    @GET(Urls.USER_INFO_URL)
    Call<UserInfoToken> getUserInfo(@Header("Authorization") String token);

    @GET(Urls.TRANSACTION_URL)
    Call<TransactionListObject> getTransactionsList(@Header("Authorization") String token);

    @FormUrlEncoded
    @POST(Urls.TRANSACTION_URL)
    Call<TransactionObject> createTransaction(@Header("Authorization") String token,
                                              @Field("name") String name,
                                              @Field("amount") String amount);

    @FormUrlEncoded
    @POST(Urls.USERS_LIST_URL)
    Call<List<UserListObject>> getUsersList(@Header("Authorization") String token,
                                            @Field("filter") String filter);
}
