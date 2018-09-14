package com.iamagamedev.sharpdeveloperstestjava.repository.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TransactionObject implements Parcelable{

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("date")
    @Expose
    private String date;

    @SerializedName("username")
    @Expose
    private String username;

    @SerializedName("amount")
    @Expose
    private String amount;

    @SerializedName("balance")
    @Expose
    private String balance;

    protected TransactionObject(Parcel in) {
        id = in.readString();
        date = in.readString();
        username = in.readString();
        amount = in.readString();
        balance = in.readString();
    }

    public static final Creator<TransactionObject> CREATOR = new Creator<TransactionObject>() {
        @Override
        public TransactionObject createFromParcel(Parcel in) {
            return new TransactionObject(in);
        }

        @Override
        public TransactionObject[] newArray(int size) {
            return new TransactionObject[size];
        }
    };

    public String getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public String getUsername() {
        return username;
    }

    public String getAmount() {
        return amount;
    }

    public String getBalance() {
        return balance;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(date);
        parcel.writeString(username);
        parcel.writeString(amount);
        parcel.writeString(balance);
    }
}
