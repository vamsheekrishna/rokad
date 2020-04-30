package com.rokad.rokad_api.endpoints.pojos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseGetHistory extends BaseResponse {
    @SerializedName("data")
    @Expose
    LastTransaction[] lastTransaction;

    public LastTransaction[] getLastTransaction() {
        return lastTransaction;
    }

    public void setLastTransaction(LastTransaction[] lastTransaction) {
        this.lastTransaction = lastTransaction;
    }
}
