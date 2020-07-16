package com.rokad.rokad_api.endpoints.pojos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AEPSBank {
    @SerializedName("bankname")
    @Expose
    String bankName;

    @SerializedName("iin")
    @Expose
    String iin;

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getIin() {
        return iin;
    }

    public void setIin(String iin) {
        this.iin = iin;
    }
}
