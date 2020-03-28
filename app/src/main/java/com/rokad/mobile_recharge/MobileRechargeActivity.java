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
import com.rokad.utilities.views.BaseActivity;

public class MobileRechargeActivity extends BaseActivity {

    private ViewPager viewPager;
    private MobileRechargePagerAdapter viewPagerAdapter;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobile_recharge);
        assert getSupportActionBar() != null;   //null check
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);   //show back button

        viewPager =  findViewById(R.id.mobile_recharge_pager);
        viewPagerAdapter = new MobileRechargePagerAdapter(getSupportFragmentManager(),0);
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout = findViewById(R.id.mobile_tabs);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setSelectedTabIndicator(R.drawable.rounded_tab_selected_indicator);
        tabLayout.setTabRippleColor(ColorStateList.valueOf(getResources().getColor(R.color.tab_normal_color)));
    }

//    private void createTabIcons() {
//
//        TextView tabOne = (TextView) LayoutInflater.from(this).inflate(R.layout.mobile_recharge_rabs, null);
//        tabLayout.getTabAt(0).setCustomView(tabOne);
//
//        TextView tabTwo = (TextView) LayoutInflater.from(this).inflate(R.layout.mobile_recharge_rabs, null);
//
//        tabLayout.getTabAt(1).setCustomView(tabTwo);
//    }
    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

}
