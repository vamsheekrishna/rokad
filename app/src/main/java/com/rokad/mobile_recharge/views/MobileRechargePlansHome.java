package com.rokad.mobile_recharge.views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.rokad.R;
import com.rokad.mobile_recharge.adapters.RechargePlansPagerAdapter;
import com.rokad.mobile_recharge.models.mPlans.RechargePlans;
import com.rokad.utilities.views.BaseFragment;

import java.io.Serializable;
import java.util.List;


public class MobileRechargePlansHome extends BaseFragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public static final String TOP_UP = "top_up";
    public static final String ROAMING = "roaming";
    public static final String COMBO = "combo";
    public static final String CUTTER = "cutter";
    public static final String SPECIAL_PLANS = "special_plans";
    public static final String SM = "sm";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private TabLayout plansTabs;
    private ViewPager plansPager;
    private RechargePlansPagerAdapter viewPagerAdapter;

    private List<RechargePlans> topup;
    private List<RechargePlans> roaming;
    private List<RechargePlans> combo;
    private List<RechargePlans> ratecutter;
    private List<RechargePlans> sm;
    private List<RechargePlans> specialPlans;

    public MobileRechargePlansHome() {
        // Required empty public constructor
    }


    public static BaseFragment newInstance(List<RechargePlans> topup, List<RechargePlans> roaming, List<RechargePlans> combo,
                                           List<RechargePlans> ratecutter, List<RechargePlans> sm, List<RechargePlans> specialPlans) {
        MobileRechargePlansHome fragment = new MobileRechargePlansHome();
        Bundle args = new Bundle();
        args.putSerializable(TOP_UP, (Serializable) topup);
        args.putSerializable(ROAMING, (Serializable) roaming);
        args.putSerializable(COMBO, (Serializable) combo);
        args.putSerializable(CUTTER, (Serializable) ratecutter);
        args.putSerializable(SM, (Serializable) sm);
        args.putSerializable(SPECIAL_PLANS, (Serializable) specialPlans);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

            topup = (List<RechargePlans>) getArguments().getSerializable(TOP_UP);
            roaming = (List<RechargePlans>) getArguments().getSerializable(ROAMING);
            combo = (List<RechargePlans>) getArguments().getSerializable(COMBO);
            ratecutter = (List<RechargePlans>) getArguments().getSerializable(CUTTER);
            sm = (List<RechargePlans>) getArguments().getSerializable(SM);
            specialPlans = (List<RechargePlans>) getArguments().getSerializable(SPECIAL_PLANS);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_mobile_recharge_plans_home, container, false);

         plansTabs = view.findViewById(R.id.recharge_plan_tabs);
         plansPager = view.findViewById(R.id.plans_pager);
         viewPagerAdapter = new RechargePlansPagerAdapter(getChildFragmentManager(),0,topup,roaming,combo,ratecutter,sm, specialPlans);
         plansPager.setAdapter(viewPagerAdapter);
         plansTabs.setupWithViewPager(plansPager);

        return view;
    }
}
