package com.rokad.dmt.pojos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.rokad.dmt.pojos.SenderRegistration.SenderData;
import com.rokad.rokad_api.endpoints.pojos.BaseResponse;

import java.io.Serializable;

public class SenderRegistrationResponsePOJO extends BaseResponse implements Serializable{
    @SerializedName("error")
    @Expose
    String error;

    @SerializedName("data")
    @Expose
    private SenderData senderData;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public SenderData getSenderData() {
        return senderData;
    }

    public void setSenderData(SenderData senderData) {
        this.senderData = senderData;
    }
}
