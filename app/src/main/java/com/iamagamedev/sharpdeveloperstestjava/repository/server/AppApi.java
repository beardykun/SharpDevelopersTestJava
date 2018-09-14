package com.iamagamedev.sharpdeveloperstestjava.repository.server;

import com.iamagamedev.sharpdeveloperstestjava.repository.models.TokenObject;
import com.iamagamedev.sharpdeveloperstestjava.repository.models.TransactionListObject;
import com.iamagamedev.sharpdeveloperstestjava.repository.models.TransactionObject;
import com.iamagamedev.sharpdeveloperstestjava.repository.models.UserInfoToken;
import com.iamagamedev.sharpdeveloperstestjava.repository.models.UserListObject;
import com.iamagamedev.sharpdeveloperstestjava.repository.models.UserObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface AppApi {

    @FormUrlEncoded
    @POST("users")
    Call<TokenObject> registerUser(@Field("username") String username,
                                   @Field("password") String password,
                                   @Field("email") String email);

    @FormUrlEncoded
    @POST("/sessions/create")
    Call<TokenObject> loginUser(@Field("email") String email,
                                @Field("password") String password);

    @GET("/api/protected/user-info")
    Call<UserInfoToken> getUserInfo(@Header("Authorization") String token);

    @GET("/api/protected/transactions")
    Call<TransactionListObject> getTransactionsList(@Header("Authorization") String token);

    @FormUrlEncoded
    @POST("/api/protected/transactions")
    Call<TransactionObject> createTransaction(@Header("Authorization") String token,
                                              @Field("name") String name,
                                              @Field("amount") String amount);

    @FormUrlEncoded
    @POST("/api/protected/users/list")
    Call<List<UserListObject>> getUsersList(@Header("Authorization") String token,
                                            @Field("filter")String filter);
}
