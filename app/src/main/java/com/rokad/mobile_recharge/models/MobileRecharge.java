package com.rokad.mobile_recharge.models;

import com.rokad.BuildConfig;

public class MobileRecharge {

    private String rechargeFrom = "";
    private String planType = "Prepaid Mobile";
    private String service = "TSO";
    private String mobileNumber = "";
    private String rechargeAmount ="";
    private String userID = "462";
    private String preOperator = ""; // V
    private String mobileOperator =""; // Vodafone
    private String rechargeType = "0"; //TODO: pass 0 for topup and 1 for any chosen plan
    private String stateName;
    private String paymentType = "cash";

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getMobileAppVersionId() {
        return mobileAppVersionId;
    }

    public void setMobileAppVersionId(String mobileAppVersionId) {
        this.mobileAppVersionId = mobileAppVersionId;
    }

    public String getMobileApp() {
        return mobileApp;
    }

    public void setMobileApp(String mobileApp) {
        this.mobileApp = mobileApp;
    }

    private String mobileAppVersionId = BuildConfig.MOBILE_VERSION_ID;
    private String mobileApp = BuildConfig.MOBILE_APPLICATION;

    public String getRecType() {
        return recType;
    }

    public void setRecType(String recType) {
        this.recType = recType;
    }

    private String recType = "Prepaid";
    private int image;

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getRechargeFrom() {
        return rechargeFrom;
    }

    public void setRechargeFrom(String rechargeFrom) {
        this.rechargeFrom = rechargeFrom;
    }

    public String getPlanType() {
        return planType;
    }

    public void setPlanType(String planType) {
        this.planType = planType;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getPreOperator() {
        return preOperator;
    }

    public void setPreOperator(String preOperator) {
        this.preOperator = preOperator;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getRechargeAmount() {
        return rechargeAmount;
    }

    public void setRechargeAmount(String rechargeAmount) {
        this.rechargeAmount = rechargeAmount;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getMobileOperator() {
        return mobileOperator;
    }

    public void setMobileOperator(String mobileOperator) {
        this.mobileOperator = mobileOperator;
    }

    public String getRechargeType() {
        return rechargeType;
    }

    public void setRechargeType(String rechargeType) {
        this.rechargeType = rechargeType;
    }

}
