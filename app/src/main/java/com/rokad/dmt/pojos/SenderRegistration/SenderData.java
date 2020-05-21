package com.rokad.dmt.pojos.SenderRegistration;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class SenderData implements Serializable
{
    @SerializedName("sessionId")
    @Expose
    private String sessionId;

    @SerializedName("requestType")
    @Expose
    private Integer requestType;

    @SerializedName("username")
    @Expose
    private String username;

    @SerializedName("icwCode")
    @Expose
    private String icwCode;

    @SerializedName("responseCode")
    @Expose
    private String responseCode;

    @SerializedName("responseDesc")
    @Expose
    private String responseDesc;

    @SerializedName("firstName")
    @Expose
    private String firstName;

    @SerializedName("lastName")
    @Expose
    private String lastName;

    @SerializedName("mobileNo")
    @Expose
    private String mobileNo;

    @SerializedName("stateId")
    @Expose
    private String stateId;

    @SerializedName("registrationId")
    @Expose
    private String registrationId;
    @SerializedName("paytmUserState")
    @Expose
    private String paytmUserState;

//    @SerializedName("easyWalletList")
//    @Expose
//    private EasyWalletList easyWalletList;

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public Integer getRequestType() {
        return requestType;
    }

    public void setRequestType(Integer requestType) {
        this.requestType = requestType;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getIcwCode() {
        return icwCode;
    }

    public void setIcwCode(String icwCode) {
        this.icwCode = icwCode;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseDesc() {
        return responseDesc;
    }

    public void setResponseDesc(String responseDesc) {
        this.responseDesc = responseDesc;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getStateId() {
        return stateId;
    }

    public void setStateId(String stateId) {
        this.stateId = stateId;
    }

    public String getRegistrationId() {
        return registrationId;
    }

    public void setRegistrationId(String registrationId) {
        this.registrationId = registrationId;
    }

    public String getPaytmUserState() {
        return paytmUserState;
    }

    public void setPaytmUserState(String paytmUserState) {
        this.paytmUserState = paytmUserState;
    }

//    public EasyWalletList getEasyWalletList() {
//        return easyWalletList;
//    }
//
//    public void setEasyWalletList(EasyWalletList easyWalletList) {
//        this.easyWalletList = easyWalletList;
//    }
}
