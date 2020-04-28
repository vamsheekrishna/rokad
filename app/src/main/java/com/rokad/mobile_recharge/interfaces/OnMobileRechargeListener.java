package com.rokad.mobile_recharge.interfaces;

import com.rokad.mobile_recharge.models.MobileRecharge;
import com.rokad.mobile_recharge.models.mPlans.RechargePlans;
import com.rokad.mobile_recharge.models.mPlans.Records;

import java.util.List;

public interface OnMobileRechargeListener {
    MobileRecharge getMobileRechargeModule();
    void goToMakePaymentFragment();
    void goToSeePlansFragment(List<RechargePlans> topup);
    void makeAnotherPayment();
    void updatePrice();

    void goToSeePlansFragment(Records data);
}
