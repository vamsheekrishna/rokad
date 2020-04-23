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
    public final static Parcelable.Creator<EasyWalletList> CREATOR = new Parcelable.Creator<EasyWalletList>() {


        @SuppressWarnings({
                "unchecked"
        })
        public EasyWalletList createFromParcel(Parcel in) {
            return new EasyWalletList(in);
        }

        public EasyWalletList[] newArray(int size) {
            return (new EasyWalletList[size]);
        }

    }
            ;
    private final static long serialVersionUID = -3005205088715925473L;

    protected EasyWalletList(Parcel in) {
        this.easyWallet = ((EasyWallet) in.readValue((EasyWallet.class.getClassLoader())));
    }

    public EasyWalletList() {
    }

    public EasyWallet getEasyWallet() {
        return easyWallet;
    }

    public void setEasyWallet(EasyWallet easyWallet) {
        this.easyWallet = easyWallet;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(easyWallet);
    }

    public int describeContents() {
        return 0;
    }
}
