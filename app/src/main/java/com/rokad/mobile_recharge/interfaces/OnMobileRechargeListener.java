package com.rokad.mobile_recharge.interfaces;

import com.rokad.mobile_recharge.models.MobileRecharge;
import com.rokad.mobile_recharge.models.mPlans.SM;

import java.util.List;

public interface OnMobileRechargeListener {
    MobileRecharge getMobileRechargeModule();
    void goToMakePaymentFragment();
    void goToSeePlansFragment(List<SM> topup, List<SM> roaming, List<SM> combo, List<SM> ratecutter, List<SM> sm);
    void makeAnotherPayment();
    void updatePrice();
}
