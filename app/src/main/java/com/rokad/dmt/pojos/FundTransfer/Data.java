package com.rokad.dmt.pojos.FundTransfer;

public class Data
{
    private String sessionId;

    private String sourceId;

    private int requestType;

    private String username;

    private String icwCode;

    private String responseCode;

    private String responseDesc;

    private int senderMobileNo;

    private String senderName;

    private String senderId;

    private String processingBankName;

    private String processingBankId;

    private String beneficiaryId;

    private int amount;

    private String remitType;

    private String paymentMode;

    private int processingfee;

    private int netAmount;

    private EasyWalletList easyWalletList;

    public void setSessionId(String sessionId){
        this.sessionId = sessionId;
    }
    public String getSessionId(){
        return this.sessionId;
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
    public void setSenderMobileNo(int senderMobileNo){
        this.senderMobileNo = senderMobileNo;
    }
    public int getSenderMobileNo(){
        return this.senderMobileNo;
    }
    public void setSenderName(String senderName){
        this.senderName = senderName;
    }
    public String getSenderName(){
        return this.senderName;
    }
    public void setSenderId(String senderId){
        this.senderId = senderId;
    }
    public String getSenderId(){
        return this.senderId;
    }
    public void setProcessingBankName(String processingBankName){
        this.processingBankName = processingBankName;
    }
    public String getProcessingBankName(){
        return this.processingBankName;
    }
    public void setProcessingBankId(String processingBankId){
        this.processingBankId = processingBankId;
    }
    public String getProcessingBankId(){
        return this.processingBankId;
    }
    public void setBeneficiaryId(String beneficiaryId){
        this.beneficiaryId = beneficiaryId;
    }
    public String getBeneficiaryId(){
        return this.beneficiaryId;
    }
    public void setAmount(int amount){
        this.amount = amount;
    }
    public int getAmount(){
        return this.amount;
    }
    public void setRemitType(String remitType){
        this.remitType = remitType;
    }
    public String getRemitType(){
        return this.remitType;
    }
    public void setPaymentMode(String paymentMode){
        this.paymentMode = paymentMode;
    }
    public String getPaymentMode(){
        return this.paymentMode;
    }
    public void setProcessingfee(int processingfee){
        this.processingfee = processingfee;
    }
    public int getProcessingfee(){
        return this.processingfee;
    }
    public void setNetAmount(int netAmount){
        this.netAmount = netAmount;
    }
    public int getNetAmount(){
        return this.netAmount;
    }
    public void setEasyWalletList(EasyWalletList easyWalletList){
        this.easyWalletList = easyWalletList;
    }
    public EasyWalletList getEasyWalletList(){
        return this.easyWalletList;
    }
}

