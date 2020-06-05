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

        @SerializedName("sourceid")
        @Expose
        String sourceid;

        @SerializedName("mastercode")
        @Expose
        String mastercode;

        @SerializedName("agentcode")
        @Expose
        String agentcode;

        @SerializedName("trackid")
        @Expose
        String trackid;

        @SerializedName("checksum")
        @Expose
        String checksum;

        @SerializedName("ebixurl")
        @Expose
        String ebixurl;

        @SerializedName("rokadurl")
        @Expose
        String rokadurl;

        @SerializedName("request_from")
        @Expose
        String request_from;

        public String getEbixurl() {
            return ebixurl;
        }

        public void setEbixurl(String ebixurl) {
            this.ebixurl = ebixurl;
        }

        public String getRokadurl() {
            return rokadurl;
        }

        public void setRokadurl(String rokadurl) {
            this.rokadurl = rokadurl;
        }

        public String getRequest_from() {
            return request_from;
        }

        public void setRequest_from(String request_from) {
            this.request_from = request_from;
        }

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

        public String getSourceid() {
            return sourceid;
        }

        public void setSourceid(String sourceid) {
            this.sourceid = sourceid;
        }

        public String getTrackid() {
            return trackid;
        }

        public void setTrackid(String trackid) {
            this.trackid = trackid;
        }

        public String getChecksum() {
            return checksum;
        }

        public void setChecksum(String checksum) {
            this.checksum = checksum;
        }
    }
}
