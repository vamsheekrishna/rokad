package com.rokad.rokad_api.endpoints;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.rokad.rokad_api.endpoints.pojos.BaseResponse;

public class ResponseResendBCSenderVerified extends BaseResponse {
    @SerializedName("data")
    @Expose()
    private
    BCSenderVerifiedData bcSenderVerifiedData;

    public BCSenderVerifiedData getBcSenderVerifiedData() {
        return bcSenderVerifiedData;
    }

    public void setBcSenderVerifiedData(BCSenderVerifiedData bcSenderVerifiedData) {
        this.bcSenderVerifiedData = bcSenderVerifiedData;
    }

    public class BCSenderVerifiedData {
        String sessionId;
        String sourceId;
        String requestType;
        String username;
        String icwCode;
        String responseCode;
        String responseDesc;
        String mobileNo;

        public String getSessionId() {
            return sessionId;
        }

        public void setSessionId(String sessionId) {
            this.sessionId = sessionId;
        }

        public String getSourceId() {
            return sourceId;
        }

        public void setSourceId(String sourceId) {
            this.sourceId = sourceId;
        }

        public String getRequestType() {
            return requestType;
        }

        public void setRequestType(String requestType) {
            this.requestType = requestType;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getIcwCode() {
            return icwCode;
        }

        public void setIcwCode(String icwCode) {
            this.icwCode = icwCode;
        }

        public String getResponseCode() {
            return responseCode;
        }

        public void setResponseCode(String responseCode) {
            this.responseCode = responseCode;
        }

        public String getResponseDesc() {
            return responseDesc;
        }

        public void setResponseDesc(String responseDesc) {
            this.responseDesc = responseDesc;
        }

        public String getMobileNo() {
            return mobileNo;
        }

        public void setMobileNo(String mobileNo) {
            this.mobileNo = mobileNo;
        }
    }

}
