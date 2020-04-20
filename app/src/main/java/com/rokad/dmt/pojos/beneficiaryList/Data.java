package com.rokad.dmt.pojos.beneficiaryList;

public class Data {
    private String sessionId;

    private String sourceId;

    private int requestType;

    private String username;

    private String icwCode;

    private String responseCode;

    private String responseDesc;

    private int senderMobileNo;

    private String processingBankName;

    private String processingBankId;

    private String senderId;

    private String senderName;

    private String neftLimit;

    private String impsLimit;

    private int neftLimitRs;

    private int impsLimitRs;

    private String kycVerified;

    private String isSenderAvailable;

    private String bcSenderVerified;

    private int bcLimitRs;

    private int ebixLimitRs;

    private String bankId;

    private String isPPi;

    private EasyWalletList easyWalletList;

    private Beneficiaries beneficiaries;

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
    public void setSenderId(String senderId){
        this.senderId = senderId;
    }
    public String getSenderId(){
        return this.senderId;
    }
    public void setSenderName(String senderName){
        this.senderName = senderName;
    }
    public String getSenderName(){
        return this.senderName;
    }
    public void setNeftLimit(String neftLimit){
        this.neftLimit = neftLimit;
    }
    public String getNeftLimit(){
        return this.neftLimit;
    }
    public void setImpsLimit(String impsLimit){
        this.impsLimit = impsLimit;
    }
    public String getImpsLimit(){
        return this.impsLimit;
    }
    public void setNeftLimitRs(int neftLimitRs){
        this.neftLimitRs = neftLimitRs;
    }
    public int getNeftLimitRs(){
        return this.neftLimitRs;
    }
    public void setImpsLimitRs(int impsLimitRs){
        this.impsLimitRs = impsLimitRs;
    }
    public int getImpsLimitRs(){
        return this.impsLimitRs;
    }
    public void setKycVerified(String kycVerified){
        this.kycVerified = kycVerified;
    }
    public String getKycVerified(){
        return this.kycVerified;
    }
    public void setIsSenderAvailable(String isSenderAvailable){
        this.isSenderAvailable = isSenderAvailable;
    }
    public String getIsSenderAvailable(){
        return this.isSenderAvailable;
    }
    public void setBcSenderVerified(String bcSenderVerified){
        this.bcSenderVerified = bcSenderVerified;
    }
    public String getBcSenderVerified(){
        return this.bcSenderVerified;
    }
    public void setBcLimitRs(int bcLimitRs){
        this.bcLimitRs = bcLimitRs;
    }
    public int getBcLimitRs(){
        return this.bcLimitRs;
    }
    public void setEbixLimitRs(int ebixLimitRs){
        this.ebixLimitRs = ebixLimitRs;
    }
    public int getEbixLimitRs(){
        return this.ebixLimitRs;
    }
    public void setBankId(String bankId){
        this.bankId = bankId;
    }
    public String getBankId(){
        return this.bankId;
    }
    public void setIsPPi(String isPPi){
        this.isPPi = isPPi;
    }
    public String getIsPPi(){
        return this.isPPi;
    }
    public void setEasyWalletList(EasyWalletList easyWalletList){
        this.easyWalletList = easyWalletList;
    }
    public EasyWalletList getEasyWalletList(){
        return this.easyWalletList;
    }
    public void setBeneficiaries(Beneficiaries beneficiaries){
        this.beneficiaries = beneficiaries;
    }
    public Beneficiaries getBeneficiaries(){
        return this.beneficiaries;
    }
}
