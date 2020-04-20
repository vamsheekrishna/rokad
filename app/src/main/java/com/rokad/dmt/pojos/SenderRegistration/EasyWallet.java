package com.rokad.dmt.pojos.SenderRegistration;


import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class EasyWallet {
    private String easyWalletId;

    private String easyWalletType;

    private double easyWalletBalance;

    public void setEasyWalletId(String easyWalletId){
        this.easyWalletId = easyWalletId;
    }
    public String getEasyWalletId(){
        return this.easyWalletId;
    }
    public void setEasyWalletType(String easyWalletType){
        this.easyWalletType = easyWalletType;
    }
    public String getEasyWalletType(){
        return this.easyWalletType;
    }
    public void setEasyWalletBalance(double easyWalletBalance){
        this.easyWalletBalance = easyWalletBalance;
    }
    public double getEasyWalletBalance(){
        return this.easyWalletBalance;
    }

}

