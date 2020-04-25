package com.rokad.dmt.RequestPackets;

public class FundTransferDetails {
    private String mobileNumber;
    private String senderName;
    private String impsTransferLimit;
    private String neftTransferLimit;
    private String registrationID;
    private String confirmedAmount;
    private String chosenBeneficiary;
    private String prefferedTransferType;

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getImpsTransferLimit() {
        return impsTransferLimit;
    }

    public void setImpsTransferLimit(String impsTransferLimit) {
        this.impsTransferLimit = impsTransferLimit;
    }

    public String getNeftTransferLimit() {
        return neftTransferLimit;
    }

    public void setNeftTransferLimit(String neftTransferLimit) {
        this.neftTransferLimit = neftTransferLimit;
    }

    public String getRegistrationID() {
        return registrationID;
    }

    public void setRegistrationID(String registrationID) {
        this.registrationID = registrationID;
    }

    public String getConfirmedAmount() {
        return confirmedAmount;
    }

    public void setConfirmedAmount(String confirmedAmount) {
        this.confirmedAmount = confirmedAmount;
    }

    public String getChosenBeneficiary() {
        return chosenBeneficiary;
    }

    public void setChosenBeneficiary(String chosenBeneficiary) {
        this.chosenBeneficiary = chosenBeneficiary;
    }

    public String getPrefferedTransferType() {
        return prefferedTransferType;
    }

    public void setPrefferedTransferType(String prefferedTransferType) {
        this.prefferedTransferType = prefferedTransferType;
    }
}
