package com.rokad.mobile_recharge;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.rokad.R;
import com.rokad.authentication.UserData;
import com.rokad.utilities.views.BaseActivity;

public class MobileRechargeActivity extends BaseActivity implements OnMobileRechargeListener{
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
        finish();
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
}
