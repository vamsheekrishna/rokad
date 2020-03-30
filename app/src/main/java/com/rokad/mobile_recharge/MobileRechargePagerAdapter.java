package com.rokad.mobile_recharge;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.rokad.utilities.views.BaseFragment;

import java.util.ArrayList;

public class MobileRechargePagerAdapter extends FragmentPagerAdapter {

    private ArrayList<BaseFragment> fragments;
    private ArrayList<String> fragTitles;

    public MobileRechargePagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);

        fragments = new ArrayList<>();
        fragments.add(RechargeHomeFragment.newInstance("",""));
        fragments.add(MobileRechargeHistoryFragment.newInstance("",""));

        fragTitles = new ArrayList<>();
        fragTitles.add("New Recharge");
        fragTitles.add("History");
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        getPageTitle(position);
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return fragTitles.get(position);
    }
}
