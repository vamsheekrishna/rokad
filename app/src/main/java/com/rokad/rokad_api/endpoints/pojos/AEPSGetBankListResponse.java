package com.rokad.rokad_api.endpoints.pojos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class AEPSGetBankListResponse extends BaseResponse {
    @SerializedName("data")
    @Expose
    ArrayList<AEPSBank> aepsBanks;

    public ArrayList<AEPSBank> getAepsBanks() {
        return aepsBanks;
    }

    public void setAepsBanks(ArrayList<AEPSBank> aepsBanks) {
        this.aepsBanks = aepsBanks;
    }
}
