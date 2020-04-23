package com.rokad.dmt.pojos.beneficiaryList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

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