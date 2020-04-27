package com.rokad.mobile_recharge.adapters;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.rokad.mobile_recharge.models.mPlans.SM;
import com.rokad.mobile_recharge.views.SMSPlans;
import com.rokad.utilities.views.BaseFragment;

import java.util.ArrayList;
import java.util.List;

public class RechargePlansPagerAdapter extends FragmentStatePagerAdapter {


    private ArrayList<BaseFragment> rechargePlansFrags;
    private ArrayList<String> fragTitles;

    public RechargePlansPagerAdapter(@NonNull FragmentManager fm, int behavior,  List<SM> topup, List<SM> roaming, List<SM> combo, List<SM> ratecutter, List<SM> sm) {
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
            rechargePlansFrags.add(SMSPlans.newInstance(topup));
        }
        if(roaming.size() > 0 ) {
            fragTitles.add("Roaming");
            rechargePlansFrags.add(SMSPlans.newInstance(roaming));
        }
        if(combo.size() > 0 ) {
            fragTitles.add("Combo Plans");
            rechargePlansFrags.add(SMSPlans.newInstance(combo));
        }
        if(ratecutter.size() > 0 ) {
            fragTitles.add("Rate Cutters");
            rechargePlansFrags.add(SMSPlans.newInstance(ratecutter));
        }
        if(sm.size() > 0 ) {
            fragTitles.add("SMS plans");
            rechargePlansFrags.add(SMSPlans.newInstance(sm));
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
