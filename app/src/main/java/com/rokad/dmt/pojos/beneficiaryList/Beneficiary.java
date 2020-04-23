package com.rokad.dmt.pojos.beneficiaryList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.ArrayList;


public class Beneficiary {

    @SerializedName("beneficiaryId")
    @Expose
    private String beneficiaryId;
    @SerializedName("beneficiaryFullName")
    @Expose
    private String beneficiaryFullName;
    @SerializedName("beneficiaryRelation")
    @Expose
    private String beneficiaryRelation;
    @SerializedName("beneficiaryBankName")
    @Expose
    private String beneficiaryBankName;
    @SerializedName("impsAvailable")
    @Expose
    private String impsAvailable;
    @SerializedName("impsStatus")
    @Expose
    private String impsStatus;
    @SerializedName("beneficiaryMobileNo")
    @Expose
    private Integer beneficiaryMobileNo;
    @SerializedName("beneficiaryAccountNo")
    @Expose
    private Integer beneficiaryAccountNo;
    @SerializedName("ifscCode")
    @Expose
    private String ifscCode;
    @SerializedName("verified")
    @Expose
    private String verified;
    @SerializedName("verifiedDate")
    @Expose
    private List<Object> verifiedDate = null;
    @SerializedName("verifiedName")
    @Expose
    private List<Object> verifiedName = null;

    public String getBeneficiaryId() {
        return beneficiaryId;
    }

    public void setBeneficiaryId(String beneficiaryId) {
        this.beneficiaryId = beneficiaryId;
    }

    public String getBeneficiaryFullName() {
        return beneficiaryFullName;
    }

    public void setBeneficiaryFullName(String beneficiaryFullName) {
        this.beneficiaryFullName = beneficiaryFullName;
    }

    public String getBeneficiaryRelation() {
        return beneficiaryRelation;
    }

    public void setBeneficiaryRelation(String beneficiaryRelation) {
        this.beneficiaryRelation = beneficiaryRelation;
    }

    public String getBeneficiaryBankName() {
        return beneficiaryBankName;
    }

    public void setBeneficiaryBankName(String beneficiaryBankName) {
        this.beneficiaryBankName = beneficiaryBankName;
    }

    public String getImpsAvailable() {
        return impsAvailable;
    }

    public void setImpsAvailable(String impsAvailable) {
        this.impsAvailable = impsAvailable;
    }

    public String getImpsStatus() {
        return impsStatus;
    }

    public void setImpsStatus(String impsStatus) {
        this.impsStatus = impsStatus;
    }

    public Integer getBeneficiaryMobileNo() {
        return beneficiaryMobileNo;
    }

    public void setBeneficiaryMobileNo(Integer beneficiaryMobileNo) {
        this.beneficiaryMobileNo = beneficiaryMobileNo;
    }

    public Integer getBeneficiaryAccountNo() {
        return beneficiaryAccountNo;
    }

    public void setBeneficiaryAccountNo(Integer beneficiaryAccountNo) {
        this.beneficiaryAccountNo = beneficiaryAccountNo;
    }

    public String getIfscCode() {
        return ifscCode;
    }

    public void setIfscCode(String ifscCode) {
        this.ifscCode = ifscCode;
    }

    public String getVerified() {
        return verified;
    }

    public void setVerified(String verified) {
        this.verified = verified;
    }

    public List<Object> getVerifiedDate() {
        return verifiedDate;
    }

    public void setVerifiedDate(List<Object> verifiedDate) {
        this.verifiedDate = verifiedDate;
    }

    public List<Object> getVerifiedName() {
        return verifiedName;
    }

    public void setVerifiedName(List<Object> verifiedName) {
        this.verifiedName = verifiedName;
    }
}
