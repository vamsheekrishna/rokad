package com.rokad.dmt.pojos;

import com.rokad.dmt.pojos.SenderRegistration.Data;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SenderRegistrationResponsePOJO {
    private String status;

    private Data data;

    public void setStatus(String status){
        this.status = status;
    }
    public String getStatus(){
        return this.status;
    }
    public void setData(Data data){
        this.data = data;
    }
    public Data getData(){
        return this.data;
    }
}
