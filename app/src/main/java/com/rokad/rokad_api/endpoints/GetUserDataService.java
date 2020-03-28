package com.rokad.rokad_api.endpoints;

import com.rokad.rokad_api.endpoints.pojos.ResponseUser;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface GetUserDataService {
    static String URL_LOGIN_BASE = "rest_server/rokad/login";

    @POST(URL_LOGIN_BASE)
    @FormUrlEncoded
    Call<ResponseUser> login(@Field("mobile_no") String mobileNo,
                             @Field("password") String password);
}
