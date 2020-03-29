package com.rokad.rokad_api.endpoints;

import com.rokad.rokad_api.endpoints.pojos.ResponseForgotPassword;
import com.rokad.rokad_api.endpoints.pojos.ResponseUser;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

import static com.rokad.rokad_api.endpoints.URL.FORGOT_PASSWORD;
import static com.rokad.rokad_api.endpoints.URL.LOGIN;

public interface AuthenticationService {
    @POST(LOGIN)
    @FormUrlEncoded
    Call<ResponseUser> login(@Field("mobile_no") String mobileNo,
                             @Field("password") String password);

    @POST(FORGOT_PASSWORD)
    @FormUrlEncoded
    Call<ResponseForgotPassword> forgotPassword(@Field("email") String email);
}
