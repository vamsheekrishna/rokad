package com.rokad.rokad_api.endpoints;

import com.rokad.BuildConfig;
import com.rokad.dmt.pojos.TransactionProcessPOJO;
import com.rokad.rokad_api.endpoints.pojos.AEPSGetBankListResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface AEPSService {
    @POST(BuildConfig.AEPS_TRANSACTION_PROCESS)
    @FormUrlEncoded
    public Call<TransactionProcessPOJO> transactionProcess(@Field("transCode") String transCode,
                                                           @Field("amount") int amount,
                                                           @Field("bankIINNumber") String bankIINNumber,
                                                           @Field("xmlBiometricString") String xmlBiometricString,
                                                           @Field("user_id") String user_id,
                                                           @Field("mobile") String mobileNumber,
                                                           @Field("aadharNumber") String aadharNumber
    );
    @POST(BuildConfig.AEPS_GET_BANK_DETAILS)
    Call<AEPSGetBankListResponse> getBankList();

}
