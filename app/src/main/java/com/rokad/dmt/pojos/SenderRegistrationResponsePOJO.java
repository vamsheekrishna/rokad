package com.rokad.dmt.pojos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.rokad.dmt.pojos.SenderRegistration.SenderData;

import java.io.Serializable;

public class SenderRegistrationResponsePOJO implements Serializable{
    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("msg")
    @Expose
    String msg;

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

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public SenderData getSenderData() {
        return senderData;
    }

    public void setSenderData(SenderData senderData) {
        this.senderData = senderData;
    }
}
