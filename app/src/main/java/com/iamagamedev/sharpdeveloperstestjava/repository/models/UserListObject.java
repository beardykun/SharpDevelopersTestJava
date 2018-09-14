package com.iamagamedev.sharpdeveloperstestjava.repository.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserListObject implements Parcelable{

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("name")
    @Expose
    private String name;

    protected UserListObject(Parcel in) {
        id = in.readString();
        name = in.readString();
    }

    public static final Creator<UserListObject> CREATOR = new Creator<UserListObject>() {
        @Override
        public UserListObject createFromParcel(Parcel in) {
            return new UserListObject(in);
        }

        @Override
        public UserListObject[] newArray(int size) {
            return new UserListObject[size];
        }
    };

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(name);
    }
}
