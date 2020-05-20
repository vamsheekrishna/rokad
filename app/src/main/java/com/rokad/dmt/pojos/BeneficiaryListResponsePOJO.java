package com.rokad.dmt.pojos;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.rokad.dmt.pojos.beneficiaryList.Data;
import com.rokad.rokad_api.endpoints.pojos.BaseResponse;

import java.io.Serializable;

public class BeneficiaryListResponsePOJO extends BaseResponse implements Serializable{

    @SerializedName("data")
    @Expose
    private Data data;

    public Data getData() {
        return data;
    }
    public void setData(Data data) {
        this.data = data;
    }

}

