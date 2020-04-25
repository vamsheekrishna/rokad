
package com.rokad.mobile_recharge.models.mPlans;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Records {

    @SerializedName("TOPUP")
    @Expose
    private List<TOPUP> tOPUP = new ArrayList<TOPUP>();
    @SerializedName("RATE CUTTER")
    @Expose
    private List<RATECUTTER> rATECUTTER = new ArrayList<RATECUTTER>();
    @SerializedName("SMS")
    @Expose
    private List<SM> sMS = new ArrayList<SM>();
    @SerializedName("Romaing")
    @Expose
    private List<Romaing> romaing = new ArrayList<Romaing>();
    @SerializedName("COMBO")
    @Expose
    private List<COMBO> cOMBO = new ArrayList<COMBO>();

    public List<TOPUP> getTOPUP() {
        return tOPUP;
    }

    public void setTOPUP(List<TOPUP> tOPUP) {
        this.tOPUP = tOPUP;
    }

    public List<RATECUTTER> getRATECUTTER() {
        return rATECUTTER;
    }

    public void setRATECUTTER(List<RATECUTTER> rATECUTTER) {
        this.rATECUTTER = rATECUTTER;
    }

    public List<SM> getSMS() {
        return sMS;
    }

    public void setSMS(List<SM> sMS) {
        this.sMS = sMS;
    }

    public List<Romaing> getRomaing() {
        return romaing;
    }

    public void setRomaing(List<Romaing> romaing) {
        this.romaing = romaing;
    }

    public List<COMBO> getCOMBO() {
        return cOMBO;
    }

    public void setCOMBO(List<COMBO> cOMBO) {
        this.cOMBO = cOMBO;
    }

}
