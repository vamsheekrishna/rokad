package com.rokad.dmt.pojos;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.rokad.rokad_api.endpoints.pojos.BaseResponse;

import java.io.Serializable;
import java.util.List;

public class BankListResponsePOJO extends BaseResponse implements Serializable {

    @SerializedName("data")
    @Expose
    List<BanksList> banksLists;

    public List<BanksList> getBanksLists() {
        return banksLists;
    }

    public void setBanksLists(List<BanksList> banksLists) {
        this.banksLists = banksLists;
    }

    public static class BanksList {
        @SerializedName("bankId")
        @Expose
        private String bankId;

        @SerializedName("bankName")
        @Expose
        private String bankName;

        public BanksList(String id, String name) {
            setBankId(id);
            setBankName(name);
        }

        public String getBankId() {
            return bankId;
        }

        public void setBankId(String bankId) {
            this.bankId = bankId;
        }

        public String getBankName() {
            return bankName;
        }

        public void setBankName(String bankName) {
            this.bankName = bankName;
        }
    }
}
