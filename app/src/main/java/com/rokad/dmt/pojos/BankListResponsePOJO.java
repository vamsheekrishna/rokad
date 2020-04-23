package com.rokad.dmt.pojos;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class BankListResponsePOJO implements Serializable {
    @SerializedName("bankId")
    @Expose
    private String bankId;
    @SerializedName("bankName")
    @Expose
    private String bankName;
    private final static long serialVersionUID = 150143044848584022L;

    public String getBankId() {
        return bankId;
    }

    public void setBankId(String bankId) {
        this.bankId = bankId;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }
}
