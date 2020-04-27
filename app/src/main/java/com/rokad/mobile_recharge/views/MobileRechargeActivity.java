package com.rokad.mobile_recharge.views;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import com.rokad.R;
import com.rokad.authentication.UserData;
import com.rokad.mobile_recharge.interfaces.OnMobileRechargeListener;
import com.rokad.mobile_recharge.models.MobileRecharge;
import com.rokad.mobile_recharge.models.mPlans.RechargePlans;
import com.rokad.utilities.views.ServicesBaseActivity;

import java.util.List;

public class MobileRechargeActivity extends ServicesBaseActivity implements OnMobileRechargeListener {
    MobileRecharge mobileRecharge = new MobileRecharge();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_root_view);
        mobileRecharge.setUserID(UserData.getUserData().getId());
        addFragment(MobileHomeFragment.newInstance("",""),"MobileHomeFragment",false);
    }

    @Override
    public MobileRecharge getMobileRechargeModule() {
        return mobileRecharge;
    }

    @Override
    public void goToMakePaymentFragment() {
        replaceFragment(MakePaymentFragment.newInstance("",""), "MakePaymentFragment", true);
    }

    @Override
    public void goToSeePlansFragment(List<RechargePlans> topup, List<RechargePlans> roaming, List<RechargePlans> combo, List<RechargePlans> ratecutter, List<RechargePlans> sm) {
        replaceFragment(MobileRechargePlansHome.newInstance(topup,roaming,combo,ratecutter,sm), "MobileRechargePlansHome", true);
    }

    @Override
    public void makeAnotherPayment() {
        replaceFragment(MobileHomeFragment.newInstance("",""),"MobileHomeFragment",false);
    }

    @Override
    public void updatePrice() {
        Fragment navHostFragment = getSupportFragmentManager().getPrimaryNavigationFragment();
        assert navHostFragment != null;
        RechargeHomeFragment fragment = (RechargeHomeFragment) navHostFragment.getChildFragmentManager().getFragments().get(0);
        fragment.onDetach();
        fragment.onAttach(getApplicationContext());
        fragment.onResume();
    }
}
