package com.rokad.rokad_api.endpoints;

import com.rokad.rokad_api.endpoints.pojos.ResponseForgotPassword;
import com.rokad.rokad_api.endpoints.pojos.ResponseUser;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface GetUserDataService {
    String URL_LOGIN_BASE = "rest_server/rokad/";
    String LOGIN = URL_LOGIN_BASE+"login";
    String FORGOT_PASSWORD = URL_LOGIN_BASE+"forgot_password";
    @POST(LOGIN)
    @FormUrlEncoded
    Call<ResponseUser> login(@Field("mobile_no") String mobileNo,
                             @Field("password") String password);

    @POST(FORGOT_PASSWORD)
    @FormUrlEncoded
    Call<ResponseForgotPassword> forgotPassword(@Field("email") String email);
}
