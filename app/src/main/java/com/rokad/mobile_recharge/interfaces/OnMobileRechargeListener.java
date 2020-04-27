package com.rokad.mobile_recharge.interfaces;

import com.rokad.mobile_recharge.models.MobileRecharge;
import com.rokad.mobile_recharge.models.mPlans.PostpaidData;
import com.rokad.mobile_recharge.models.mPlans.RechargePlans;

import java.util.List;

public interface OnMobileRechargeListener {
    MobileRecharge getMobileRechargeModule();
    void goToMakePaymentFragment();
    void goToSeePlansFragment(List<RechargePlans> topup, List<RechargePlans> roaming, List<RechargePlans> combo, List<RechargePlans> ratecutter, List<RechargePlans> sm);
    void makeAnotherPayment();
    void updatePrice();

    void goToSeePlansFragment(PostpaidData data);
}
