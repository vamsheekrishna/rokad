package com.rokad.rokad_api.endpoints;

import com.rokad.rokad_api.endpoints.pojos.ResponseMobileRecharge;
import com.rokad.rokad_api.endpoints.pojos.ResponseUser;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

import static com.rokad.rokad_api.endpoints.URL.LOGIN;
import static com.rokad.rokad_api.endpoints.URL.RECHARGE;

public interface MobileRechargeService {

    @POST(RECHARGE)
    @FormUrlEncoded
    Call<ResponseMobileRecharge> recharge(@Field("recharge_from") String rechargeFrom,
                                       @Field("plan_type") String planType,
                                       @Field("service") String service,
                                       @Field("pre_operator") String preOperator,
                                       @Field("mobile_number") String mobileNumber,
                                       @Field("recharge_amount") String rechargeAmount,
                                       @Field("user_id") String userID,
                                       @Field("mobile_operator") String mobileOperator,
                                       @Field("recharge_type") String rechargeType);
}
