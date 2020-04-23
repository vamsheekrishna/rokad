package com.rokad.dmt.pojos.SenderRegistration;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Data
{
    @SerializedName("sessionId")
    @Expose
    private String sessionId;
    @SerializedName("checkSum")
    @Expose
    private List<Object> checkSum = new ArrayList<Object>();
    @SerializedName("sourceId")
    @Expose
    private String sourceId;
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
    private Integer mobileNo;
    @SerializedName("stateId")
    @Expose
    private Integer stateId;
    @SerializedName("registrationId")
    @Expose
    private String registrationId;
    @SerializedName("paytmUserState")
    @Expose
    private String paytmUserState;
    @SerializedName("easyWalletList")
    @Expose
    private EasyWalletList easyWalletList;
    public final static Parcelable.Creator<Data> CREATOR = new Parcelable.Creator<Data>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Data createFromParcel(Parcel in) {
            return new Data(in);
        }

        public Data[] newArray(int size) {
            return (new Data[size]);
        }

    }
            ;
    private final static long serialVersionUID = -5253945984504606033L;

    protected Data(Parcel in) {
        this.sessionId = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.checkSum, (java.lang.Object.class.getClassLoader()));
        this.sourceId = ((String) in.readValue((String.class.getClassLoader())));
        this.requestType = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.username = ((String) in.readValue((String.class.getClassLoader())));
        this.icwCode = ((String) in.readValue((String.class.getClassLoader())));
        this.responseCode = ((String) in.readValue((String.class.getClassLoader())));
        this.responseDesc = ((String) in.readValue((String.class.getClassLoader())));
        this.firstName = ((String) in.readValue((String.class.getClassLoader())));
        this.lastName = ((String) in.readValue((String.class.getClassLoader())));
        this.mobileNo = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.stateId = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.registrationId = ((String) in.readValue((String.class.getClassLoader())));
        this.paytmUserState = ((String) in.readValue((String.class.getClassLoader())));
        this.easyWalletList = ((EasyWalletList) in.readValue((EasyWalletList.class.getClassLoader())));
    }

    public Data() {
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public List<Object> getCheckSum() {
        return checkSum;
    }

    public void setCheckSum(List<Object> checkSum) {
        this.checkSum = checkSum;
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
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

    public Integer getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(Integer mobileNo) {
        this.mobileNo = mobileNo;
    }

    public Integer getStateId() {
        return stateId;
    }

    public void setStateId(Integer stateId) {
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

    public EasyWalletList getEasyWalletList() {
        return easyWalletList;
    }

    public void setEasyWalletList(EasyWalletList easyWalletList) {
        this.easyWalletList = easyWalletList;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(sessionId);
        dest.writeList(checkSum);
        dest.writeValue(sourceId);
        dest.writeValue(requestType);
        dest.writeValue(username);
        dest.writeValue(icwCode);
        dest.writeValue(responseCode);
        dest.writeValue(responseDesc);
        dest.writeValue(firstName);
        dest.writeValue(lastName);
        dest.writeValue(mobileNo);
        dest.writeValue(stateId);
        dest.writeValue(registrationId);
        dest.writeValue(paytmUserState);
        dest.writeValue(easyWalletList);
    }

    public int describeContents() {
        return 0;
    }

}
