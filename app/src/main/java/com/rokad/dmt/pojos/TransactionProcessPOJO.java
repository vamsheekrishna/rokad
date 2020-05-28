package com.rokad.dmt.pojos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.rokad.rokad_api.endpoints.pojos.BaseResponse;

import java.io.Serializable;

public class TransactionProcessPOJO extends BaseResponse {
    @SerializedName("data")
    @Expose
    private
    TransactionProcessData data;

    public TransactionProcessData getData() {
        return data;
    }

    public void setData(TransactionProcessData data) {
        this.data = data;
    }

    public class TransactionProcessData implements Serializable {
        @SerializedName("request_id")
        @Expose
        String request_id;

        @SerializedName("agentcode")
        @Expose
        String agentcode;

        @SerializedName("mastercode")
        @Expose
        String mastercode;

        @SerializedName("RETURNURL")
        @Expose
        String RETURNURL;

        public String getRequest_id() {
            return request_id;
        }

        public void setRequest_id(String request_id) {
            this.request_id = request_id;
        }

        public String getAgentcode() {
            return agentcode;
        }

        public void setAgentcode(String agentcode) {
            this.agentcode = agentcode;
        }

        public String getMastercode() {
            return mastercode;
        }

        public void setMastercode(String mastercode) {
            this.mastercode = mastercode;
        }

        public String getRETURNURL() {
            return RETURNURL;
        }

        public void setRETURNURL(String RETURNURL) {
            this.RETURNURL = RETURNURL;
        }
    }
}
