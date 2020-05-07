package com.rokad.dmt.pojos.SenderRegistration;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

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
}

