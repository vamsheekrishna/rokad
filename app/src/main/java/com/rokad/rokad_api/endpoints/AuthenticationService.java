package com.rokad.rokad_api.endpoints;

import com.rokad.BuildConfig;
import com.rokad.rokad_api.endpoints.pojos.ResponseForgotPassword;
import com.rokad.rokad_api.endpoints.pojos.ResponseUser;
import com.rokad.rokad_api.endpoints.pojos.ResponseWalletBalance;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;


public interface AuthenticationService {
    @POST(BuildConfig.LOGIN)
    @FormUrlEncoded
    Call<ResponseUser> login(@Field("mobile_no") String mobileNo,
                             @Field("password") String password);

    @POST(BuildConfig.FORGOT_PASSWORD)
    @FormUrlEncoded
    Call<ResponseForgotPassword> forgotPassword(@Field("email") String email);

    @POST(BuildConfig.WALLET_BALANCE)
    @FormUrlEncoded
    Call<ResponseWalletBalance> getWalletBalance(@Field("user_id") String userID);
}
