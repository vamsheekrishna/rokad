package com.rokad.mobile_recharge;

import android.os.Bundle;

import com.rokad.R;
import com.rokad.authentication.UserData;
import com.rokad.mobile_recharge.interfaces.OnMobileRechargeListener;
import com.rokad.mobile_recharge.models.MobileRecharge;
import com.rokad.mobile_recharge.views.MakePaymentFragment;
import com.rokad.mobile_recharge.views.MobileHomeFragment;
import com.rokad.mobile_recharge.views.MobileRechargePlansHome;
import com.rokad.utilities.views.BaseActivity;

public class MobileRechargeActivity extends BaseActivity implements OnMobileRechargeListener {
    MobileRecharge mobileRecharge = new MobileRecharge();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobile_recharge);

        assert getSupportActionBar() != null;   //null check
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);   //show back button
        mobileRecharge.setUserID(UserData.getUserData().getId());
        addFragment(MobileHomeFragment.newInstance("",""),"MobileHomeFragment",false);
    }

    @Override
    public boolean onSupportNavigateUp(){
        onBackPressed();
        return true;
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
