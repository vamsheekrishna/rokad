package com.rokad.dmt.pojos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class TransactionDate implements Serializable {
    @SerializedName("sessionId")
    @Expose
    private
    String sessionId;

    @SerializedName("sourceId")
    @Expose
    private String sourceId;
    @SerializedName("requestType")
    @Expose
    private String requestType;
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
    @SerializedName("senderName")
    @Expose
    private String senderName;
    @SerializedName("senderId")
    @Expose
    private String senderId;
    @SerializedName("processingBankName")
    @Expose
    private String processingBankName;
    @SerializedName("processingBankId")
    @Expose
    private String processingBankId;
    @SerializedName("beneficiaryId")
    @Expose
    private String beneficiaryId;
    @SerializedName("amount")
    @Expose
    private String amount;
    @SerializedName("remitType")
    @Expose
    private String remitType;
    @SerializedName("paymentMode")
    @Expose
    private String paymentMode;
    @SerializedName("processingfee")
    @Expose
    private String processingfee;
    @SerializedName("netAmount")
    @Expose
    private String netAmount;
    @SerializedName("orderId")
    @Expose
    private String orderId;
    @SerializedName("transactionId")
    @Expose
    private String transactionId;
    @SerializedName("impsResponseCode")
    @Expose
    private String impsResponseCode;
    @SerializedName("rrnNo")
    @Expose
    private String rrnNo;

    private String beneficiaryName;
    private String beneficiaryMobile;

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

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
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

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
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

    public String getBeneficiaryId() {
        return beneficiaryId;
    }

    public void setBeneficiaryId(String beneficiaryId) {
        this.beneficiaryId = beneficiaryId;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getRemitType() {
        return remitType;
    }

    public void setRemitType(String remitType) {
        this.remitType = remitType;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    public String getProcessingfee() {
        return processingfee;
    }

    public void setProcessingfee(String processingfee) {
        this.processingfee = processingfee;
    }

    public String getNetAmount() {
        return netAmount;
    }

    public void setNetAmount(String netAmount) {
        this.netAmount = netAmount;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getImpsResponseCode() {
        return impsResponseCode;
    }

    public void setImpsResponseCode(String impsResponseCode) {
        this.impsResponseCode = impsResponseCode;
    }

    public String getRrnNo() {
        return rrnNo;
    }

    public void setRrnNo(String rrnNo) {
        this.rrnNo = rrnNo;
    }

    public String getBeneficiaryName() {
        return beneficiaryName;
    }

    public void setBeneficiaryName(String beneficiaryName) {
        this.beneficiaryName = beneficiaryName;
    }

    public String getBeneficiaryMobile() {
        return beneficiaryMobile;
    }

    public void setBeneficiaryMobile(String beneficiaryMobile) {
        this.beneficiaryMobile = beneficiaryMobile;
    }
}
