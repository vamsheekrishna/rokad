package com.rokad.dmt.pojos.beneficiaryList;

import java.util.List;
import java.util.ArrayList;


public class Beneficiary
{
    private String beneficiaryId;

    private String beneficiaryFullName;

    private String beneficiaryRelation;

    private String beneficiaryBankName;

    private String impsAvailable;

    private String impsStatus;

    private int beneficiaryMobileNo;

    private int beneficiaryAccountNo;

    private String ifscCode;

    private String verified;

    private String verifiedDate;

    private List<String> verifiedName;

    public void setBeneficiaryId(String beneficiaryId){
        this.beneficiaryId = beneficiaryId;
    }
    public String getBeneficiaryId(){
        return this.beneficiaryId;
    }
    public void setBeneficiaryFullName(String beneficiaryFullName){
        this.beneficiaryFullName = beneficiaryFullName;
    }
    public String getBeneficiaryFullName(){
        return this.beneficiaryFullName;
    }
    public void setBeneficiaryRelation(String beneficiaryRelation){
        this.beneficiaryRelation = beneficiaryRelation;
    }
    public String getBeneficiaryRelation(){
        return this.beneficiaryRelation;
    }
    public void setBeneficiaryBankName(String beneficiaryBankName){
        this.beneficiaryBankName = beneficiaryBankName;
    }
    public String getBeneficiaryBankName(){
        return this.beneficiaryBankName;
    }
    public void setImpsAvailable(String impsAvailable){
        this.impsAvailable = impsAvailable;
    }
    public String getImpsAvailable(){
        return this.impsAvailable;
    }
    public void setImpsStatus(String impsStatus){
        this.impsStatus = impsStatus;
    }
    public String getImpsStatus(){
        return this.impsStatus;
    }
    public void setBeneficiaryMobileNo(int beneficiaryMobileNo){
        this.beneficiaryMobileNo = beneficiaryMobileNo;
    }
    public int getBeneficiaryMobileNo(){
        return this.beneficiaryMobileNo;
    }
    public void setBeneficiaryAccountNo(int beneficiaryAccountNo){
        this.beneficiaryAccountNo = beneficiaryAccountNo;
    }
    public int getBeneficiaryAccountNo(){
        return this.beneficiaryAccountNo;
    }
    public void setIfscCode(String ifscCode){
        this.ifscCode = ifscCode;
    }
    public String getIfscCode(){
        return this.ifscCode;
    }
    public void setVerified(String verified){
        this.verified = verified;
    }
    public String getVerified(){
        return this.verified;
    }
    public void setVerifiedDate(String verifiedDate){
        this.verifiedDate = verifiedDate;
    }
    public String getVerifiedDate(){
        return this.verifiedDate;
    }
    public void setVerifiedName(List<String> verifiedName){
        this.verifiedName = verifiedName;
    }
    public List<String> getVerifiedName(){
        return this.verifiedName;
    }
}
