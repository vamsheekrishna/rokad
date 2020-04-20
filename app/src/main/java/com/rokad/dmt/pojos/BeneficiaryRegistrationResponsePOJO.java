package com.rokad.dmt.pojos;

import com.rokad.dmt.pojos.BeneficiaryRegistration.Data;

public class BeneficiaryRegistrationResponsePOJO {
    private String status;

    private String msg;

    private Data data;

    public void setStatus(String status){
        this.status = status;
    }
    public String getStatus(){
        return this.status;
    }
    public void setMsg(String msg){
        this.msg = msg;
    }
    public String getMsg(){
        return this.msg;
    }
    public void setData(Data data){
        this.data = data;
    }
    public Data getData(){
        return this.data;
    }

}
