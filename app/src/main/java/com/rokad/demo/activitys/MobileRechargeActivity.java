package com.rokad.demo.activitys;

import android.os.Bundle;

import com.rokad.demo.R;
import com.rokad.demo.fragments.MobileRechargeFragment;
import com.rokad.demo.fragments.MobileRechargePlans;
import com.rokad.demo.interfaces.OnMobileRechargeInteractionListener;

public class MobileRechargeActivity extends BaseActivity implements OnMobileRechargeInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        addFragment(MobileRechargeFragment.newInstance(" ", " "), "MobileRechargeFragment", false);
    }

    @Override
    public void goToViewMobilePlanScreen() {
        replaceFragment(MobileRechargePlans.newInstance("",""),"" ,true);
    }

    @Override
    public void goToViewCommissionScreen() {
        replaceFragment(MobileRechargePlans.newInstance("",""),"" ,true);
    }
}
