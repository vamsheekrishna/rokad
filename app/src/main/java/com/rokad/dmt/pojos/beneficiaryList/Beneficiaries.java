package com.rokad.dmt.pojos.beneficiaryList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Beneficiaries implements Serializable {
    @SerializedName("beneficiary")
    @Expose
    private List<Beneficiary> beneficiary = new ArrayList<>();

    public List<Beneficiary> getBeneficiary() {
        return beneficiary;
    }

    public void setBeneficiary(List<Beneficiary> beneficiary) {
        this.beneficiary = beneficiary;
    }
}
