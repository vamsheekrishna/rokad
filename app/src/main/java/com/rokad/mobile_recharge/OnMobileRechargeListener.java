package com.rokad.mobile_recharge;

interface OnMobileRechargeListener {
    MobileRecharge getMobileRechargeModule();
    void goToMakePaymentFragment();
    void goToSeePlansFragment();
    void makeAnotherPayment();
}
