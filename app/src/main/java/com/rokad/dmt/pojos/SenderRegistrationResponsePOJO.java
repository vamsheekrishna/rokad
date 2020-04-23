package com.rokad.dmt.pojos;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.rokad.dmt.pojos.SenderRegistration.Data;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SenderRegistrationResponsePOJO implements Serializable, Parcelable {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("data")
    @Expose
    private Data data;
    public final static Parcelable.Creator<SenderRegistrationResponsePOJO> CREATOR = new Creator<SenderRegistrationResponsePOJO>() {


        @SuppressWarnings({
                "unchecked"
        })
        public SenderRegistrationResponsePOJO createFromParcel(Parcel in) {
            return new SenderRegistrationResponsePOJO(in);
        }

        public SenderRegistrationResponsePOJO[] newArray(int size) {
            return (new SenderRegistrationResponsePOJO[size]);
        }

    }
            ;
    private final static long serialVersionUID = -3361088673564887700L;

    protected SenderRegistrationResponsePOJO(Parcel in) {
        this.status = ((String) in.readValue((String.class.getClassLoader())));
        this.data = ((Data) in.readValue((Data.class.getClassLoader())));
    }

    public SenderRegistrationResponsePOJO() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(status);
        dest.writeValue(data);
    }

    public int describeContents() {
        return 0;
    }
}
