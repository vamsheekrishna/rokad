package com.rokad.dmt.pojos;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.rokad.dmt.pojos.beneficiaryList.Data;
import com.rokad.rokad_api.endpoints.pojos.BaseResponse;

import java.io.Serializable;

public class BeneficiaryListResponsePOJO extends BaseResponse implements Parcelable, Serializable{

    @SerializedName("data")
    @Expose
    private Data data;

    protected BeneficiaryListResponsePOJO(Parcel in) {
    }

    public static final Creator<BeneficiaryListResponsePOJO> CREATOR = new Creator<BeneficiaryListResponsePOJO>() {
        @Override
        public BeneficiaryListResponsePOJO createFromParcel(Parcel in) {
            return new BeneficiaryListResponsePOJO(in);
        }

        @Override
        public BeneficiaryListResponsePOJO[] newArray(int size) {
            return new BeneficiaryListResponsePOJO[size];
        }
    };

    public Data getData() {
        return data;
    }
    public void setData(Data data) {
        this.data = data;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }
}

