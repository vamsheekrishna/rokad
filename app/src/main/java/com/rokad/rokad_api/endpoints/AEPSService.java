package com.rokad.rokad_api.endpoints;

import com.rokad.BuildConfig;
import com.rokad.dmt.pojos.TransactionProcessPOJO;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface AEPSService {
    @POST(BuildConfig.AEPS_TRANSACTION_PROCESS)
    @FormUrlEncoded
    public Call<TransactionProcessPOJO> transactionProcess(@Field("FirstName") String firstName,
                                                           @Field("LastName") String lastName,
                                                           @Field("CustomerMobileNo") String mobileNumber,
                                                           @Field("CustomerAmount") String amt,
                                                           @Field("user_id") String user_id);
}
