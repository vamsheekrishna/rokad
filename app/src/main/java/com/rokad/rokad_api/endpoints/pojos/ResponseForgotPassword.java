package com.rokad.rokad_api.endpoints.pojos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseForgotPassword extends BaseResponse{
    @SerializedName("new pass")
    @Expose
    private String newPass;
}
