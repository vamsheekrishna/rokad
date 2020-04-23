package com.rokad.dmt.pojos.SenderRegistration;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class EasyWallet {
    @SerializedName("easyWalletId")
    @Expose
    private String easyWalletId;
    @SerializedName("easyWalletType")
    @Expose
    private String easyWalletType;
    @SerializedName("easyWalletBalance")
    @Expose
    private Double easyWalletBalance;
    public final static Parcelable.Creator<EasyWallet> CREATOR = new Parcelable.Creator<EasyWallet>() {


        @SuppressWarnings({
                "unchecked"
        })
        public EasyWallet createFromParcel(Parcel in) {
            return new EasyWallet(in);
        }

        public EasyWallet[] newArray(int size) {
            return (new EasyWallet[size]);
        }

    }
            ;
    private final static long serialVersionUID = 4989318857800673110L;

    protected EasyWallet(Parcel in) {
        this.easyWalletId = ((String) in.readValue((String.class.getClassLoader())));
        this.easyWalletType = ((String) in.readValue((String.class.getClassLoader())));
        this.easyWalletBalance = ((Double) in.readValue((Double.class.getClassLoader())));
    }

    public EasyWallet() {
    }

    public String getEasyWalletId() {
        return easyWalletId;
    }

    public void setEasyWalletId(String easyWalletId) {
        this.easyWalletId = easyWalletId;
    }

    public String getEasyWalletType() {
        return easyWalletType;
    }

    public void setEasyWalletType(String easyWalletType) {
        this.easyWalletType = easyWalletType;
    }

    public Double getEasyWalletBalance() {
        return easyWalletBalance;
    }

    public void setEasyWalletBalance(Double easyWalletBalance) {
        this.easyWalletBalance = easyWalletBalance;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(easyWalletId);
        dest.writeValue(easyWalletType);
        dest.writeValue(easyWalletBalance);
    }

    public int describeContents() {
        return 0;
    }
}

