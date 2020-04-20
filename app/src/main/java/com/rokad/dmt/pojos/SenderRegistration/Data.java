package com.rokad.dmt.pojos.SenderRegistration;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Data
{
    private String sessionId;

    private List<String> checkSum;

    private String sourceId;

    private int requestType;

    private String username;

    private String icwCode;

    private String responseCode;

    private String responseDesc;

    private String firstName;

    private String lastName;

    private int mobileNo;

    private int stateId;

    private String registrationId;

    private String paytmUserState;

    private EasyWalletList easyWalletList;

    public void setSessionId(String sessionId){
        this.sessionId = sessionId;
    }
    public String getSessionId(){
        return this.sessionId;
    }
    public void setCheckSum(List<String> checkSum){
        this.checkSum = checkSum;
    }
    public List<String> getCheckSum(){
        return this.checkSum;
    }
    public void setSourceId(String sourceId){
        this.sourceId = sourceId;
    }
    public String getSourceId(){
        return this.sourceId;
    }
    public void setRequestType(int requestType){
        this.requestType = requestType;
    }
    public int getRequestType(){
        return this.requestType;
    }
    public void setUsername(String username){
        this.username = username;
    }
    public String getUsername(){
        return this.username;
    }
    public void setIcwCode(String icwCode){
        this.icwCode = icwCode;
    }
    public String getIcwCode(){
        return this.icwCode;
    }
    public void setResponseCode(String responseCode){
        this.responseCode = responseCode;
    }
    public String getResponseCode(){
        return this.responseCode;
    }
    public void setResponseDesc(String responseDesc){
        this.responseDesc = responseDesc;
    }
    public String getResponseDesc(){
        return this.responseDesc;
    }
    public void setFirstName(String firstName){
        this.firstName = firstName;
    }
    public String getFirstName(){
        return this.firstName;
    }
    public void setLastName(String lastName){
        this.lastName = lastName;
    }
    public String getLastName(){
        return this.lastName;
    }
    public void setMobileNo(int mobileNo){
        this.mobileNo = mobileNo;
    }
    public int getMobileNo(){
        return this.mobileNo;
    }
    public void setStateId(int stateId){
        this.stateId = stateId;
    }
    public int getStateId(){
        return this.stateId;
    }
    public void setRegistrationId(String registrationId){
        this.registrationId = registrationId;
    }
    public String getRegistrationId(){
        return this.registrationId;
    }
    public void setPaytmUserState(String paytmUserState){
        this.paytmUserState = paytmUserState;
    }
    public String getPaytmUserState(){
        return this.paytmUserState;
    }
    public void setEasyWalletList(EasyWalletList easyWalletList){
        this.easyWalletList = easyWalletList;
    }
    public EasyWalletList getEasyWalletList(){
        return this.easyWalletList;
    }
}
