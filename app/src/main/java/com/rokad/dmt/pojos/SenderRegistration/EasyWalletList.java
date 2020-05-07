package com.rokad.dmt.pojos.SenderRegistration;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class EasyWalletList
{
    @SerializedName("easyWallet")
    @Expose
    private EasyWallet easyWallet;

    public EasyWallet getEasyWallet() {
        return easyWallet;
    }
    public void setEasyWallet(EasyWallet easyWallet) {
        this.easyWallet = easyWallet;
    }
}
