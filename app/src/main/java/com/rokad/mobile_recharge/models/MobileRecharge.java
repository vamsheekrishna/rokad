package com.rokad.mobile_recharge.models;

import com.rokad.BuildConfig;
import com.rokad.R;
import com.rokad.authentication.UserData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MobileRecharge {

    private ArrayList<SubscriberModule> prepaidSubscriberList;
    HashMap<String,  SubscriberModule> prepaidSubscriberMap;
    private String rechargeFrom = "mobile";
    private String planType = "";
    private String service = "TSO";
    private String mobileNumber = "";
    private String rechargeAmount ="";
    private String userID = "";
    private String preOperator = ""; // V
    private String mobileOperator =""; // Vodafone
    private String rechargeType = "0"; //TODO: pass 0 for topup and 1 for any chosen plan
    private String stateName;
    private String paymentType = "cash";
    private int selectedSubscriber = -1;

    public MobileRecharge() {
        this.prepaidSubscriberList = new ArrayList<>();
        prepaidSubscriberMap = new HashMap<>();
        this.prepaidSubscriberList.add(new SubscriberModule(0, R.drawable.airtel, "AirtelExpress", "AE","Airtel"));
        prepaidSubscriberMap.put("AE", prepaidSubscriberList.get(0));
        this.prepaidSubscriberList.add(new SubscriberModule(1, R.drawable.reliance, "Reliance GSM", "RG",""));
        prepaidSubscriberMap.put("RG", prepaidSubscriberList.get(1));
        this.prepaidSubscriberList.add(new SubscriberModule(2, R.drawable.bsnl, "BSNL", "B", "BSNL"));
        prepaidSubscriberMap.put("B", prepaidSubscriberList.get(2));
        this.prepaidSubscriberList.add(new SubscriberModule(3, R.drawable.idea, "Idea", "I", "idea"));
        prepaidSubscriberMap.put("I", prepaidSubscriberList.get(3));
        this.prepaidSubscriberList.add(new SubscriberModule(4, R.drawable.vodafone, "Vodafone", "V", "Vodafone"));
        prepaidSubscriberMap.put("V", prepaidSubscriberList.get(4));
        this.prepaidSubscriberList.add(new SubscriberModule(5,R.drawable.jio,"JOE","JOE", "jio"));
        prepaidSubscriberMap.put("JOE", prepaidSubscriberList.get(5));
        this.prepaidSubscriberList.add(new SubscriberModule(6,R.drawable.docomo,"Tata Docomo","TD",""));
        prepaidSubscriberMap.put("TD", prepaidSubscriberList.get(6));
        this.prepaidSubscriberList.add(new SubscriberModule(7,R.drawable.indicom,"Tata Indicom", "TI","Tata Indicom"));
        prepaidSubscriberMap.put("TI", prepaidSubscriberList.get(7));
        this.prepaidSubscriberList.add(new SubscriberModule(8,R.drawable.aircel,"Aircel", "AI",""));
        prepaidSubscriberMap.put("AI", prepaidSubscriberList.get(8));
        userID = UserData.getUserData().getId();
    }

    public ArrayList<SubscriberModule> getPrepaidSubscriberList() {
        this.prepaidSubscriberList = new ArrayList<>();
        prepaidSubscriberMap = new HashMap<>();
        this.prepaidSubscriberList.add(new SubscriberModule(0, R.drawable.airtel, "AirtelExpress", "AE","Airtel"));
        prepaidSubscriberMap.put("AE", prepaidSubscriberList.get(0));
        this.prepaidSubscriberList.add(new SubscriberModule(1, R.drawable.reliance, "Reliance GSM", "RG",""));
        prepaidSubscriberMap.put("RG", prepaidSubscriberList.get(1));
        this.prepaidSubscriberList.add(new SubscriberModule(2, R.drawable.bsnl, "BSNL", "B", "BSNL"));
        prepaidSubscriberMap.put("B", prepaidSubscriberList.get(2));
        this.prepaidSubscriberList.add(new SubscriberModule(3, R.drawable.idea, "Idea", "I", "idea"));
        prepaidSubscriberMap.put("I", prepaidSubscriberList.get(3));
        this.prepaidSubscriberList.add(new SubscriberModule(4, R.drawable.vodafone, "Vodafone", "V", "Vodafone"));
        prepaidSubscriberMap.put("V", prepaidSubscriberList.get(4));
        this.prepaidSubscriberList.add(new SubscriberModule(5,R.drawable.jio,"JOE","JOE", "jio"));
        prepaidSubscriberMap.put("JOE", prepaidSubscriberList.get(5));
        this.prepaidSubscriberList.add(new SubscriberModule(6,R.drawable.docomo,"Tata Docomo","TD",""));
        prepaidSubscriberMap.put("TD", prepaidSubscriberList.get(6));
        this.prepaidSubscriberList.add(new SubscriberModule(7,R.drawable.indicom,"Tata Indicom", "TI","Tata Indicom"));
        prepaidSubscriberMap.put("TI", prepaidSubscriberList.get(7));
        this.prepaidSubscriberList.add(new SubscriberModule(8,R.drawable.aircel,"Aircel", "AI",""));
        prepaidSubscriberMap.put("AI", prepaidSubscriberList.get(8));

        return prepaidSubscriberList;
    }

    public SubscriberModule getPrepaidSubscriber(String key) {
        if(prepaidSubscriberMap.containsKey(key)) {
            return prepaidSubscriberMap.get(key);
        } else {
            return prepaidSubscriberMap.get("AI");
        }
    }

    public int getSelectedSubscriber() {
        return selectedSubscriber;
    }

    public void setSelectedSubscriber(int selectedSubscriber) {
        this.selectedSubscriber = selectedSubscriber;
    }

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
