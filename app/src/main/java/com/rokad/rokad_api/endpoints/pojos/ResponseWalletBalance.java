package com.rokad.rokad_api.endpoints.pojos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseWalletBalance extends BaseResponse {
    @SerializedName("amount")
    @Expose
    private String amount;

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
