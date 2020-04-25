
package com.rokad.mobile_recharge.models.mPlans;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("records")
    @Expose
    private Records records;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("time")
    @Expose
    private Object time;

    public Records getRecords() {
        return records;
    }

    public void setRecords(Records records) {
        this.records = records;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Object getTime() {
        return time;
    }

    public void setTime(Object time) {
        this.time = time;
    }

}
