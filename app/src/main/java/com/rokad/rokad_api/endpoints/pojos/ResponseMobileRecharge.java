package com.rokad.rokad_api.endpoints.pojos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseMobileRecharge extends BaseResponse{

    @SerializedName("error")
    @Expose
    private ResponseError error;

    class ResponseError {
        @SerializedName("pre_operator")
        @Expose
        String preOperator;

        @SerializedName("mobile_number")
        @Expose
        String mobileNumber;

        @SerializedName("recharge_amount")
        @Expose
        String rechargeAmount;
    }
    class ResponseSuccess {
        @SerializedName("wallet_tran_id")
        @Expose
        String walletTranID;

        @SerializedName("transaction_id")
        @Expose
        String transactionID;
    }
}
