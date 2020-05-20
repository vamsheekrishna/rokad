package com.rokad.dmt.pojos.beneficiaryList;

import androidx.lifecycle.ViewModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Data extends ViewModel implements Serializable {
    @SerializedName("sessionId")
    @Expose
    private String sessionId;
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
    @SerializedName("senderMobileNo")
    @Expose
    private String senderMobileNo;
    @SerializedName("processingBankName")
    @Expose
    private String processingBankName;
    @SerializedName("processingBankId")
    @Expose
    private String processingBankId;
    @SerializedName("senderId")
    @Expose
    private String senderId;
    @SerializedName("senderName")
    @Expose
    private String senderName;
    @SerializedName("neftLimit")
    @Expose
    private String neftLimit;
    @SerializedName("impsLimit")
    @Expose
    private String impsLimit;
    @SerializedName("neftLimitRs")
    @Expose
    private Integer neftLimitRs;
    @SerializedName("impsLimitRs")
    @Expose
    private Integer impsLimitRs;
    @SerializedName("kycVerified")
    @Expose
    private String kycVerified;
    @SerializedName("isSenderAvailable")
    @Expose
    private String isSenderAvailable;
    @SerializedName("bcSenderVerified")
    @Expose
    private String bcSenderVerified;
    @SerializedName("bcLimitRs")
    @Expose
    private Integer bcLimitRs;
    @SerializedName("ebixLimitRs")
    @Expose
    private Integer ebixLimitRs;
    @SerializedName("bankId")
    @Expose
    private String bankId;

    @SerializedName("beneficiaries")
    @Expose
    private Beneficiaries beneficiaries;

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
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

    public String getSenderMobileNo() {
        return senderMobileNo;
    }

    public void setSenderMobileNo(String senderMobileNo) {
        this.senderMobileNo = senderMobileNo;
    }

    public String getProcessingBankName() {
        return processingBankName;
    }

    public void setProcessingBankName(String processingBankName) {
        this.processingBankName = processingBankName;
    }

    public String getProcessingBankId() {
        return processingBankId;
    }

    public void setProcessingBankId(String processingBankId) {
        this.processingBankId = processingBankId;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getNeftLimit() {
        return neftLimit;
    }

    public void setNeftLimit(String neftLimit) {
        this.neftLimit = neftLimit;
    }

    public String getImpsLimit() {
        return impsLimit;
    }

    public void setImpsLimit(String impsLimit) {
        this.impsLimit = impsLimit;
    }

    public Integer getNeftLimitRs() {
        return neftLimitRs;
    }

    public void setNeftLimitRs(Integer neftLimitRs) {
        this.neftLimitRs = neftLimitRs;
    }

    public Integer getImpsLimitRs() {
        return impsLimitRs;
    }

    public void setImpsLimitRs(Integer impsLimitRs) {
        this.impsLimitRs = impsLimitRs;
    }

    public String getKycVerified() {
        return kycVerified;
    }

    public void setKycVerified(String kycVerified) {
        this.kycVerified = kycVerified;
    }

    public String getIsSenderAvailable() {
        return isSenderAvailable;
    }

    public void setIsSenderAvailable(String isSenderAvailable) {
        this.isSenderAvailable = isSenderAvailable;
    }

    public String getBcSenderVerified() {
        return bcSenderVerified;
    }

    public void setBcSenderVerified(String bcSenderVerified) {
        this.bcSenderVerified = bcSenderVerified;
    }

    public Integer getBcLimitRs() {
        return bcLimitRs;
    }

    public void setBcLimitRs(Integer bcLimitRs) {
        this.bcLimitRs = bcLimitRs;
    }

    public Integer getEbixLimitRs() {
        return ebixLimitRs;
    }

    public void setEbixLimitRs(Integer ebixLimitRs) {
        this.ebixLimitRs = ebixLimitRs;
    }

    public String getBankId() {
        return bankId;
    }

    public void setBankId(String bankId) {
        this.bankId = bankId;
    }

    public Beneficiaries getBeneficiaries() {
        return beneficiaries;
    }

    public void setBeneficiaries(Beneficiaries beneficiaries) {
        this.beneficiaries = beneficiaries;
    }
}
