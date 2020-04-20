package com.rokad.dmt.pojos.beneficiaryList;

import java.util.List;

public class Beneficiaries {
    private List<Beneficiary> beneficiary;

    public void setBeneficiary(List<Beneficiary> beneficiary){
        this.beneficiary = beneficiary;
    }
    public List<Beneficiary> getBeneficiary(){
        return this.beneficiary;
    }
}
