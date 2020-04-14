package com.rokad.mobile_recharge.views;

import android.os.Bundle;

import com.rokad.R;
import com.rokad.authentication.UserData;
import com.rokad.home.ServicesHomeFragment;
import com.rokad.mobile_recharge.interfaces.OnMobileRechargeListener;
import com.rokad.mobile_recharge.models.MobileRecharge;
import com.rokad.utilities.views.BaseActivity;
import com.rokad.utilities.views.ServicesBaseActivity;

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
    public void goToSeePlansFragment() {
        replaceFragment(MobileRechargePlansHome.newInstance("",""), "MobileRechargePlansHome", true);
    }

    @Override
    public void makeAnotherPayment() {
        replaceFragment(MobileHomeFragment.newInstance("",""),"MobileHomeFragment",false);
    }
}
