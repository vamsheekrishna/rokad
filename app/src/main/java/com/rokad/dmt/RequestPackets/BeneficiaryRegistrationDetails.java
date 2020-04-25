package com.rokad.dmt.RequestPackets;

public class BeneficiaryRegistrationDetails {
    private String firstName;
    private String lastName;
    private String senderMobileNumber;
    private String beneficiaryMobileNumber;
    private String chosenBankName;
    private String bankAccountNumber;
    private String confirmBankAccountNumber;

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

    public String getSenderMobileNumber() {
        return senderMobileNumber;
    }

    public void setSenderMobileNumber(String senderMobileNumber) {
        this.senderMobileNumber = senderMobileNumber;
    }

    public String getBeneficiaryMobileNumber() {
        return beneficiaryMobileNumber;
    }

    public void setBeneficiaryMobileNumber(String beneficiaryMobileNumber) {
        this.beneficiaryMobileNumber = beneficiaryMobileNumber;
    }

    public String getChosenBankName() {
        return chosenBankName;
    }

    public void setChosenBankName(String chosenBankName) {
        this.chosenBankName = chosenBankName;
    }

    public String getBankAccountNumber() {
        return bankAccountNumber;
    }

    public void setBankAccountNumber(String bankAccountNumber) {
        this.bankAccountNumber = bankAccountNumber;
    }

    public String getConfirmBankAccountNumber() {
        return confirmBankAccountNumber;
    }

    public void setConfirmBankAccountNumber(String confirmBankAccountNumber) {
        this.confirmBankAccountNumber = confirmBankAccountNumber;
    }

    public String getBankIFSCCode() {
        return bankIFSCCode;
    }

    public void setBankIFSCCode(String bankIFSCCode) {
        this.bankIFSCCode = bankIFSCCode;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    private String bankIFSCCode;
    private String relation;
}
