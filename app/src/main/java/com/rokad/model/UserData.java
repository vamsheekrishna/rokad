package com.rokad.model;

import com.rokad.rokad_api.endpoints.pojos.User;
import com.rokad.utilities.encrypt.Encryption;
import com.rokad.utilities.encrypt.EncryptionFactory;
import com.rokad.utilities.encrypt.EncryptionHelper;

public class UserData {
    private static UserData userData;

    private String id;

    private String walletBalance;

    private String firstName;

    private String lastName;

    private String userName;

    private String gender;

    private String email;

    private String address1;

    private String address2;

    private String dob;

    private String pinCode;

    private String country;

    private String state;

    private String city;

    private String roleID;

    private String roleName;

    private String phoneNo;

    private String kycVerifyStatus;

    private String status;

    private String totalTransaction;

    private String totalTransactionAmt;

    private String commissionEarnedPerDay;

    private String commissionChained;

    private String agentCode;

    private String currentDate;

    private String loginAttempts;

    private String services;


    EncryptionHelper objEncryptionHelper = new EncryptionHelper();

    private UserData() {

    }
   static public void setInstance(User userData) {
        Encryption aesAlgorithm;
       try {
           aesAlgorithm = EncryptionFactory.getEncryptionByName("AES");
           String input = "Rajan";
           String key = "QWRTEfnfdys635";//E-m!tr@2016
           aesAlgorithm.setKey(key);

           getInstance().setAddress1(aesAlgorithm.decrypt(userData.getAddress1()));
           getInstance().setAddress2(aesAlgorithm.decrypt(userData.getAddress2()));
           getInstance().setAgentCode(aesAlgorithm.decrypt(userData.getAgentCode()));
           getInstance().setCity(aesAlgorithm.decrypt(userData.getCity()));
           getInstance().setCommissionChained(aesAlgorithm.decrypt(userData.getCommissionChained()));

           getInstance().setCommissionEarnedPerDay(aesAlgorithm.decrypt(userData.getCommissionEarnedPerDay()));
           getInstance().setCountry(aesAlgorithm.decrypt(userData.getCountry()));
           getInstance().setCurrentDate(aesAlgorithm.decrypt(userData.getCurrentDate()));
           getInstance().setDob(aesAlgorithm.decrypt(userData.getDob()));
           getInstance().setEmail(aesAlgorithm.decrypt(userData.getEmail()));


           getInstance().setFirstName(aesAlgorithm.decrypt(userData.getFirstName()));
           getInstance().setGender(aesAlgorithm.decrypt(userData.getGender()));
           getInstance().setId(aesAlgorithm.decrypt(userData.getId()));
           getInstance().setKycVerifyStatus(aesAlgorithm.decrypt(userData.getKycVerifyStatus()));
           getInstance().setLastName(aesAlgorithm.decrypt(userData.getLastName()));

           getInstance().setLoginAttempts(aesAlgorithm.decrypt(userData.getLoginAttempts()));
           getInstance().setPhoneNo(aesAlgorithm.decrypt(userData.getPhoneNo()));
           getInstance().setPinCode(aesAlgorithm.decrypt(userData.getPinCode()));
           getInstance().setRoleID(aesAlgorithm.decrypt(userData.getRoleID()));
           getInstance().setRoleName(aesAlgorithm.decrypt(userData.getRoleName()));

           getInstance().setServices(aesAlgorithm.decrypt(userData.getServices()));
           getInstance().setState(aesAlgorithm.decrypt(userData.getState()));
           getInstance().setStatus(aesAlgorithm.decrypt(userData.getStatus()));
           getInstance().setTotalTransaction(aesAlgorithm.decrypt(userData.getTotalTransaction()));
           getInstance().setTotalTransactionAmt(aesAlgorithm.decrypt(userData.getTotalTransactionAmt()));

           getInstance().setUserName(aesAlgorithm.decrypt(userData.getUserName()));
           getInstance().setWalletBalance(aesAlgorithm.decrypt(userData.getWalletBalance()));

       } catch (Exception e) {
           e.printStackTrace();
       }
   }

    static public UserData getInstance() {
        if(null == userData) {
            userData = new UserData();
        }
        return userData;
    }

    static public void deleteInstance() {
        userData = null;
    }

    static public boolean isSessionExpire() {
        return null == userData;
    }

    public static UserData getUserData() {
        return userData;
    }

    public static void setUserData(UserData userData) {
        UserData.userData = userData;
    }

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
        this.walletBalance = walletBalance.replace(",","");
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
}
