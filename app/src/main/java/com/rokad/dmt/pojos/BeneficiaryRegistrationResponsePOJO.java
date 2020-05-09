package com.rokad.dmt.pojos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.rokad.dmt.pojos.BeneficiaryRegistration.Data;
import com.rokad.rokad_api.endpoints.pojos.BaseResponse;

public class BeneficiaryRegistrationResponsePOJO extends BaseResponse {

    @SerializedName("data")
    @Expose
    private Data data;

    public void setData(Data data){
        this.data = data;
    }
    public Data getData(){
        return this.data;
    }

}
