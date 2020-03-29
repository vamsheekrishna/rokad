package com.rokad.mobile_recharge;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.rokad.utilities.views.BaseFragment;

import java.util.ArrayList;

public class RechargePlansPagerAdapter extends FragmentStatePagerAdapter {


    ArrayList<BaseFragment> rechargePlansFrags;
    ArrayList<String> fragTitles;

    public RechargePlansPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        rechargePlansFrags = new ArrayList<>();
        fragTitles = new ArrayList<>();
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

      rechargePlansFrags.add(RechargeTopUpPlans.newInstance("",""));
      rechargePlansFrags.add(RoamingPlans.newInstance("",""));
      rechargePlansFrags.add(ComboPlans.newInstance("",""));
      rechargePlansFrags.add(RateCutterPlans.newInstance("",""));
      rechargePlansFrags.add(SMSPlans.newInstance("",""));

        return rechargePlansFrags.get(position);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {

        fragTitles.add("TopUp");
        fragTitles.add("Roaming");
        fragTitles.add("Combo Plans");
        fragTitles.add("Rate Cutters");
        fragTitles.add("SMS plans");

        return fragTitles.get(position);
    }

    @Override
    public int getCount() {
        return rechargePlansFrags.size();
    }
}
