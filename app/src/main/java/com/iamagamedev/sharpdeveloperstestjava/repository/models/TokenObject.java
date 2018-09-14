package com.iamagamedev.sharpdeveloperstestjava.repository.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TokenObject {

    @SerializedName("id_token")
    @Expose
    private String token = "";

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
