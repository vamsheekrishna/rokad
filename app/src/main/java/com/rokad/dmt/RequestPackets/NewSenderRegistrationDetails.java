package com.rokad.dmt.RequestPackets;

import java.util.HashMap;
import java.util.Map;

public class NewSenderRegistrationDetails {

    private String firstName;
    private String lastName;
    private String mobileNumber;
    private String otp;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public Map<Integer, String> getState() {
        return state;
    }

    public void setState(Map<Integer, String> state) {
        this.state = state;
    }

    Map<Integer,String> state = new HashMap<>();
}
