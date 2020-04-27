
package com.rokad.mobile_recharge.models.mPlans;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Records {

    @SerializedName("TOPUP")
    @Expose
    private List<RechargePlans> tOPUP = new ArrayList<RechargePlans>();
    @SerializedName("RATE CUTTER")
    @Expose
    private List<RechargePlans> rATECUTTER = new ArrayList<RechargePlans>();
    @SerializedName("SMS")
    @Expose
    private List<RechargePlans> sMS = new ArrayList<RechargePlans>();
    @SerializedName("Romaing")
    @Expose
    private List<RechargePlans> romaing = new ArrayList<RechargePlans>();
    @SerializedName("COMBO")
    @Expose
    private List<RechargePlans> cOMBO = new ArrayList<RechargePlans>();

    public List<RechargePlans> getTOPUP() {
        return tOPUP;
    }

    public void setTOPUP(List<RechargePlans> tOPUP) {
        this.tOPUP = tOPUP;
    }

    public List<RechargePlans> getRATECUTTER() {
        return rATECUTTER;
    }

    public void setRATECUTTER(List<RechargePlans> rATECUTTER) {
        this.rATECUTTER = rATECUTTER;
    }

    public List<RechargePlans> getSMS() {
        return sMS;
    }

    public void setSMS(List<RechargePlans> sMS) {
        this.sMS = sMS;
    }

    public List<RechargePlans> getRomaing() {
        return romaing;
    }

    public void setRomaing(List<RechargePlans> romaing) {
        this.romaing = romaing;
    }

    public List<RechargePlans> getCOMBO() {
        return cOMBO;
    }

    public void setCOMBO(List<RechargePlans> cOMBO) {
        this.cOMBO = cOMBO;
    }

}
