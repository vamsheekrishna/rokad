package com.rokad.dmt.pojos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.rokad.rokad_api.endpoints.pojos.BaseResponse;

import java.io.Serializable;

import retrofit2.http.Field;

public class TransactionProcessPOJO extends BaseResponse {
    @SerializedName("data")
    @Expose
    TransactionProcess transactionProcess;

}
