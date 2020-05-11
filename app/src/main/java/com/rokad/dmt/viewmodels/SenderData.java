package com.rokad.dmt.viewmodels;

import androidx.lifecycle.ViewModel;

import com.rokad.dmt.pojos.beneficiaryList.Beneficiary;
import com.rokad.dmt.pojos.beneficiaryList.Data;

public class SenderData extends ViewModel {
    private Data senderData;
    String transferMode = "IMPS";
    private Beneficiary selectedBeneficiary;
    String amount;
    public Data getSenderData() {
        return senderData;
    }

    public void setSenderData(Data senderData) {
        this.senderData = senderData;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public Beneficiary getSelectedBeneficiary() {
        return selectedBeneficiary;
    }

    public void setSelectedBeneficiary(Beneficiary selectedBeneficiary) {
        this.selectedBeneficiary = selectedBeneficiary;
    }

    public String getTransferMode() {
        return transferMode;
    }

    public void setTransferMode(String transferMode) {
        this.transferMode = transferMode;
    }
}
