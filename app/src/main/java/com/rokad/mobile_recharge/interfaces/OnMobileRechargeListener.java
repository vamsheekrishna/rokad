package com.rokad.mobile_recharge.interfaces;

import com.rokad.mobile_recharge.models.MobileRecharge;
import com.rokad.mobile_recharge.models.mPlans.COMBO;
import com.rokad.mobile_recharge.models.mPlans.RATECUTTER;
import com.rokad.mobile_recharge.models.mPlans.Romaing;
import com.rokad.mobile_recharge.models.mPlans.SM;
import com.rokad.mobile_recharge.models.mPlans.TOPUP;

import java.util.List;

public interface OnMobileRechargeListener {
    MobileRecharge getMobileRechargeModule();
    void goToMakePaymentFragment();
    void goToSeePlansFragment(List<TOPUP> topup, List<Romaing> roaming, List<COMBO> combo, List<RATECUTTER> ratecutter, List<SM> sm);
    void makeAnotherPayment();
    void updatePrice();
}
