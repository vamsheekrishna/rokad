package com.rokad.rokad_api.endpoints.pojos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.rokad.utilities.encrypt.Encryption;

import java.io.Serializable;

public class User implements Serializable {
    @SerializedName("id")
    @Expose
    String id;

    @SerializedName("wallet_balance")
    @Expose
    String walletBalance;

    @SerializedName("first_name")
    @Expose
    String firstName;

    @SerializedName("last_name")
    @Expose
    String lastName;

    @SerializedName("username")
    @Expose
    String userName;

    @SerializedName("gender")
    @Expose
    String gender;

    @SerializedName("email")
    @Expose
    String email;

    @SerializedName("address1")
    @Expose
    String address1;

    @SerializedName("address2")
    @Expose
    String address2;

    @SerializedName("dob")
    @Expose
    String dob;

    @SerializedName("pincode")
    @Expose
    String pinCode;

    @SerializedName("country")
    @Expose
    String country;

    @SerializedName("state")
    @Expose
    String state;

    @SerializedName("city")
    @Expose
    String city;

    @SerializedName("role_id")
    @Expose
    String roleID;

    @SerializedName("role_name")
    @Expose
    String roleName;

    @SerializedName("phone_no")
    @Expose
    String phoneNo;

    @SerializedName("kyc_verify_status")
    @Expose
    String kycVerifyStatus;

    @SerializedName("status")
    @Expose
    String status;

    @SerializedName("total_transaction")
    @Expose
    String totalTransaction;

    @SerializedName("total_transaction_amt")
    @Expose
    String totalTransactionAmt;

    @SerializedName("commission_earned_per_day")
    @Expose
    String commissionEarnedPerDay;

    @SerializedName("commission_chained")
    @Expose
    String commissionChained;

    @SerializedName("agent_code")
    @Expose
    String agentCode;

    @SerializedName("current_date")
    @Expose
    String currentDate;

    @SerializedName("login_attempts")
    @Expose
    String loginAttempts;

    @SerializedName("services")
    @Expose
    String services;

    Encryption aesAlgorithm;

 /*   User() {
        EncryptionHelper objEncryptionHelper = new EncryptionHelper();
        try {
            aesAlgorithm = EncryptionFactory.getEncryptionByName("AES");
            String input = "Rajan";
            String key = "QWRTEfnfdys635";//E-m!tr@2016
            aesAlgorithm.setKey(key);
            // String output = aesAlgorithm.encrypt(input);
            // System.out.println("des string : " + aesAlgorithm.encrypt(input));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWalletBalance() {
        return walletBalance;
    }

    public void setWalletBalance(String walletBalance) {
        this.walletBalance = walletBalance;
    }

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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getPinCode() {
        return pinCode;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRoleID() {
        return roleID;
    }

    public void setRoleID(String roleID) {
        this.roleID = roleID;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getKycVerifyStatus() {
        return kycVerifyStatus;
    }

    public void setKycVerifyStatus(String kycVerifyStatus) {
        this.kycVerifyStatus = kycVerifyStatus;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTotalTransaction() {
        return totalTransaction;
    }

    public void setTotalTransaction(String totalTransaction) {
        this.totalTransaction = totalTransaction;
    }

    public String getTotalTransactionAmt() {
        return totalTransactionAmt;
    }

    public void setTotalTransactionAmt(String totalTransactionAmt) {
        this.totalTransactionAmt = totalTransactionAmt;
    }

    public String getCommissionEarnedPerDay() {
        return commissionEarnedPerDay;
    }

    public void setCommissionEarnedPerDay(String commissionEarnedPerDay) {
        this.commissionEarnedPerDay = commissionEarnedPerDay;
    }

    public String getCommissionChained() {
        return commissionChained;
    }

    public void setCommissionChained(String commissionChained) {
        this.commissionChained = commissionChained;
    }

    public String getAgentCode() {
        return agentCode;
    }

    public void setAgentCode(String agentCode) {
        this.agentCode = agentCode;
    }

    public String getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(String currentDate) {
        this.currentDate = currentDate;
    }

    public String getLoginAttempts() {
        return loginAttempts;
    }

    public void setLoginAttempts(String loginAttempts) {
        this.loginAttempts = loginAttempts;
    }

    public String getServices() {
        return services;
    }

    public void setServices(String services) {
        this.services = services;
    }

    public void decryptData() {
        setAddress1(aesAlgorithm.decrypt(getAddress1()));
        setAddress2(aesAlgorithm.decrypt(getAddress2()));
        setAgentCode(aesAlgorithm.decrypt(getAgentCode()));
        setCity(aesAlgorithm.decrypt(getCity()));
        setCommissionChained(aesAlgorithm.decrypt(getCommissionChained()));
    }
}
