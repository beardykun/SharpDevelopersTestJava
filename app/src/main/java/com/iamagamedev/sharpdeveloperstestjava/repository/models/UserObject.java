package com.iamagamedev.sharpdeveloperstestjava.repository.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserObject {

    @SerializedName("id")
    @Expose
    private String id = "";

    @SerializedName("name")
    @Expose
    private String name = "";

    @SerializedName("email")
    @Expose
    private String email = "";

    @SerializedName("balance")
    @Expose
    private String balance = "";

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getBalance() {
        return balance;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }
}
