package com.rokad.mobile_recharge.interfaces;

import com.rokad.mobile_recharge.models.MobileRecharge;

public interface OnMobileRechargeListener {
    MobileRecharge getMobileRechargeModule();
    void goToMakePaymentFragment();
    void goToSeePlansFragment();
    void makeAnotherPayment();
}
