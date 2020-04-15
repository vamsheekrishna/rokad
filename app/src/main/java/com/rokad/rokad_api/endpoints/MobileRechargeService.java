package com.rokad.rokad_api.endpoints;

import com.rokad.BuildConfig;
import com.rokad.rokad_api.endpoints.pojos.ResponseGetPlans;
import com.rokad.rokad_api.endpoints.pojos.ResponseMobileRecharge;
import com.rokad.rokad_api.endpoints.pojos.ResponseUser;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface MobileRechargeService {

    @POST(BuildConfig.RECHARGE)
    @FormUrlEncoded
    Call<ResponseMobileRecharge> prePaidRecharge(
            @Field("recharge_from") String rechargeFrom,
            @Field("plan_type") String planType,
            @Field("service") String service,
            @Field("pre_operator") String preOperator,
            @Field("mobile_number") String mobileNumber,
            @Field("recharge_amount") String rechargeAmount,
            @Field("user_id") String userID,
            @Field("mobile_operator") String mobileOperator,
            @Field("rec_type") String rechargeType,
            @Field("recharge_type") String recTypeParam, //TODO: pass 0 for top-up and 1 for any chosen plan
            @Field("mobileapp") String mobileApp,
            @Field("mobileversionid") String mobileVersionID,
            @Field("payment_type") String paymentType);

    @POST(BuildConfig.RECHARGE)
    @FormUrlEncoded
    Call<ResponseMobileRecharge> postPaidRecharge(
            @Field("recharge_from") String rechargeFrom,
            @Field("plan_type") String planType,
            @Field("service") String service,
            @Field("post_operator") String preOperator,
            @Field("mobile_number") String mobileNumber,
            @Field("recharge_amount") String rechargeAmount,
            @Field("user_id") String userID,
            @Field("mobile_operator") String mobileOperator,
            @Field("rec_type") String rechargeType,
            @Field("recharge_type") String recTypeParam, //TODO: pass 0 for top-up and 1 for any chosen plan
            @Field("mobileapp") String mobileApp,
            @Field("mobileversionid") String mobileVersionID,
            @Field("payment_type") String paymentType);

    @POST(BuildConfig.GET_PLANS)
    @FormUrlEncoded
    Call<ResponseGetPlans> getPlans(
            @Field("operator") String operator,
            @Field("cricle") String cricle,
            @Field("service_type") String serviceType
    );
}
