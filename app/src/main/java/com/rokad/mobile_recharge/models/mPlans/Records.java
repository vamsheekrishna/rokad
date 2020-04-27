
package com.rokad.mobile_recharge.models.mPlans;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Records {

    @SerializedName("TOPUP")
    @Expose
    private List<SM> tOPUP = new ArrayList<SM>();
    @SerializedName("RATE CUTTER")
    @Expose
    private List<SM> rATECUTTER = new ArrayList<SM>();
    @SerializedName("SMS")
    @Expose
    private List<SM> sMS = new ArrayList<SM>();
    @SerializedName("Romaing")
    @Expose
    private List<SM> romaing = new ArrayList<SM>();
    @SerializedName("COMBO")
    @Expose
    private List<SM> cOMBO = new ArrayList<SM>();

    public List<SM> getTOPUP() {
        return tOPUP;
    }

    public void setTOPUP(List<SM> tOPUP) {
        this.tOPUP = tOPUP;
    }

    public List<SM> getRATECUTTER() {
        return rATECUTTER;
    }

    public void setRATECUTTER(List<SM> rATECUTTER) {
        this.rATECUTTER = rATECUTTER;
    }

    public List<SM> getSMS() {
        return sMS;
    }

    public void setSMS(List<SM> sMS) {
        this.sMS = sMS;
    }

    public List<SM> getRomaing() {
        return romaing;
    }

    public void setRomaing(List<SM> romaing) {
        this.romaing = romaing;
    }

    public List<SM> getCOMBO() {
        return cOMBO;
    }

    public void setCOMBO(List<SM> cOMBO) {
        this.cOMBO = cOMBO;
    }

}
