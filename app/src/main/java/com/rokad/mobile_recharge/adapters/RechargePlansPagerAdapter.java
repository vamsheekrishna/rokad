package com.rokad.mobile_recharge.adapters;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.rokad.mobile_recharge.models.mPlans.RechargePlans;
import com.rokad.mobile_recharge.views.MobileRechargePlans;
import com.rokad.utilities.views.BaseFragment;

import java.util.ArrayList;
import java.util.List;

public class RechargePlansPagerAdapter extends FragmentStatePagerAdapter {


    private ArrayList<BaseFragment> rechargePlansFrags;
    private ArrayList<String> fragTitles;

    public RechargePlansPagerAdapter(@NonNull FragmentManager fm, int behavior, List<RechargePlans> topup, List<RechargePlans> roaming, List<RechargePlans> combo, List<RechargePlans> ratecutter, List<RechargePlans> sm, List<RechargePlans> specialPlans) {
        super(fm,behavior);

        rechargePlansFrags = new ArrayList<>();
        fragTitles = new ArrayList<>();

//        rechargePlansFrags.add(RechargeTopUpPlans.newInstance(topup));
//        rechargePlansFrags.add(RoamingPlans.newInstance(roaming));
//        rechargePlansFrags.add(ComboPlans.newInstance(combo));
//        rechargePlansFrags.add(RateCutterPlans.newInstance(ratecutter));
//        rechargePlansFrags.add(SMSPlans.newInstance(sm));
//
//        fragTitles.add("TopUp");
//        fragTitles.add("Roaming");
//        fragTitles.add("Combo Plans");
//        fragTitles.add("Rate Cutters");
//        fragTitles.add("SMS plans");

        if(topup.size() > 0 ) {
            fragTitles.add("TopUp");
            rechargePlansFrags.add(MobileRechargePlans.newInstance(topup));
        }
        if(roaming.size() > 0 ) {
            fragTitles.add("Roaming");
            rechargePlansFrags.add(MobileRechargePlans.newInstance(roaming));
        }
        if(combo.size() > 0 ) {
            fragTitles.add("Combo Plans");
            rechargePlansFrags.add(MobileRechargePlans.newInstance(combo));
        }
        if(ratecutter.size() > 0 ) {
            fragTitles.add("Rate Cutters");
            rechargePlansFrags.add(MobileRechargePlans.newInstance(ratecutter));
        }
        if(sm.size() > 0 ) {
            fragTitles.add("SMS plans");
            rechargePlansFrags.add(MobileRechargePlans.newInstance(sm));
        }

        if(specialPlans.size() > 0 ) {
            fragTitles.add("Special Plans");
            rechargePlansFrags.add(MobileRechargePlans.newInstance(specialPlans));
        }
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return rechargePlansFrags.get(position);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return fragTitles.get(position);
    }

    @Override
    public int getCount() {
        return rechargePlansFrags.size();
    }


}
