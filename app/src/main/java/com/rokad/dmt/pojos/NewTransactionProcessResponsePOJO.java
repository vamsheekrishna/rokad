package com.rokad.dmt.pojos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.rokad.rokad_api.endpoints.pojos.BaseResponse;

import java.io.Serializable;

public class NewTransactionProcessResponsePOJO extends BaseResponse implements Serializable {

    @SerializedName("data")
    @Expose
    TransactionDate data;

    public TransactionDate getData() {
        return data;
    }

    public void setData(TransactionDate data) {
        this.data = data;
    }
}
