package com.rokad.dmt.pojos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.rokad.rokad_api.endpoints.pojos.BaseResponse;

public class ResendOTPResponsePOJO extends BaseResponse {

    //"data":{"sessionId":"884DMT1589347781","checkSum":123123,"sourceId":"TMX","requestType":601,"username":"ITZDS100258","icwCode":"D000096821","responseCode":"Y","responseDesc":"0,0","otp_type":"S","orderId":[],"mobileNo":7022163865}}
    @SerializedName("data")
    @Expose
    ResendOTPData resendOTPData;

    public ResendOTPData getResendOTPData() {
        return resendOTPData;
    }

    public void setResendOTPData(ResendOTPData resendOTPData) {
        this.resendOTPData = resendOTPData;
    }

    public class ResendOTPData {
        private String sessionId = "884DMT1589347781";
        private String checkSum = "123123";
        private String sourceId ="TMX";
        private String requestType ="601";
        private String username="ITZDS100258";
        private String icwCode = "D000096821";
        private String responseCode ="Y";
        private String responseDesc ="0,0";
        private String otp_type = "S";
        private String mobileNo = "7022163865";

        public String getSessionId() {
            return sessionId;
        }

        public void setSessionId(String sessionId) {
            this.sessionId = sessionId;
        }

        public String getCheckSum() {
            return checkSum;
        }

        public void setCheckSum(String checkSum) {
            this.checkSum = checkSum;
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

        public String getOtp_type() {
            return otp_type;
        }

        public void setOtp_type(String otp_type) {
            this.otp_type = otp_type;
        }

        public String getMobileNo() {
            return mobileNo;
        }

        public void setMobileNo(String mobileNo) {
            this.mobileNo = mobileNo;
        }
    }
}
