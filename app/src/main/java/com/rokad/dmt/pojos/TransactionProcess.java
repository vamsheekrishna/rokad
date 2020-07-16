package com.rokad.dmt.pojos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TransactionProcess {

    @SerializedName("responseCode")
    @Expose
    String responseCode;

    @SerializedName("agentcode")
    @Expose
    String agentcode;

    @SerializedName("rrn")
    @Expose
    String rrn;

    @SerializedName("acBalance")
    @Expose
    String acBalance;

    @SerializedName("agentId")
    @Expose
    String agentId;

    @SerializedName("txnStatus")
    @Expose
    String txnStatus;

    @SerializedName("mATMReqId")
    @Expose
    String txnAmount;

    @SerializedName("terminalId")
    @Expose
    String terminalId;

    @SerializedName("responseMessage")
    @Expose
    String responseMessage;

    @SerializedName("aadhaarNo")
    @Expose
    String aadhaarNo;

    @SerializedName("uidaiAuthCode")
    @Expose
    String uidaiAuthCode;

    @SerializedName("stan")
    @Expose
    String stan;

    @SerializedName("dateAndTime")
    @Expose
    String dateAndTime;

    @SerializedName("orderid")
    @Expose
    String orderid;

    @SerializedName("txnCode")
    @Expose
    String txnCode;

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getAgentcode() {
        return agentcode;
    }

    public void setAgentcode(String agentcode) {
        this.agentcode = agentcode;
    }

    public String getRrn() {
        return rrn;
    }

    public void setRrn(String rrn) {
        this.rrn = rrn;
    }

    public String getAcBalance() {
        return acBalance;
    }

    public void setAcBalance(String acBalance) {
        this.acBalance = acBalance;
    }

    public String getAgentId() {
        return agentId;
    }

    public void setAgentId(String agentId) {
        this.agentId = agentId;
    }

    public String getTxnStatus() {
        return txnStatus;
    }

    public void setTxnStatus(String txnStatus) {
        this.txnStatus = txnStatus;
    }

    public String getTxnAmount() {
        return txnAmount;
    }

    public void setTxnAmount(String txnAmount) {
        this.txnAmount = txnAmount;
    }

    public String getTerminalId() {
        return terminalId;
    }

    public void setTerminalId(String terminalId) {
        this.terminalId = terminalId;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public String getAadhaarNo() {
        return aadhaarNo;
    }

    public void setAadhaarNo(String aadhaarNo) {
        this.aadhaarNo = aadhaarNo;
    }

    public String getUidaiAuthCode() {
        return uidaiAuthCode;
    }

    public void setUidaiAuthCode(String uidaiAuthCode) {
        this.uidaiAuthCode = uidaiAuthCode;
    }

    public String getStan() {
        return stan;
    }

    public void setStan(String stan) {
        this.stan = stan;
    }

    public String getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(String dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public String getTxnCode() {
        return txnCode;
    }

    public void setTxnCode(String txnCode) {
        this.txnCode = txnCode;
    }
}
