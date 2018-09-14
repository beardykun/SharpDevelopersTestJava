package com.iamagamedev.sharpdeveloperstestjava.repository.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserInfoToken {

    @SerializedName("user_info_token")
    @Expose
    private UserObject userObject;

    public void setUserObject(UserObject userObject) {
        this.userObject = userObject;
    }

    public UserObject getUserObject() {
        return userObject;
    }
}
