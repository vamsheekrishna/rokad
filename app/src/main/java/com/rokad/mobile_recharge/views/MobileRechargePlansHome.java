package com.rokad.mobile_recharge.views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.rokad.R;
import com.rokad.mobile_recharge.adapters.RechargePlansPagerAdapter;
import com.rokad.mobile_recharge.models.mPlans.SM;
import com.rokad.utilities.views.BaseFragment;

import java.io.Serializable;
import java.util.List;


public class MobileRechargePlansHome extends BaseFragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private TabLayout plansTabs;
    private ViewPager plansPager;
    private RechargePlansPagerAdapter viewPagerAdapter;

    private List<SM> topup;
    private List<SM> roaming;
    private List<SM> combo;
    private List<SM> ratecutter;
    private List<SM> sm;

    public MobileRechargePlansHome() {
        // Required empty public constructor
    }


    public static BaseFragment newInstance(List<SM> topup, List<SM> roaming, List<SM> combo, List<SM> ratecutter, List<SM> sm) {
        MobileRechargePlansHome fragment = new MobileRechargePlansHome();
        Bundle args = new Bundle();
        args.putSerializable("top_up", (Serializable) topup);
        args.putSerializable("roaming", (Serializable) roaming);
        args.putSerializable("combo", (Serializable) combo);
        args.putSerializable("cutter", (Serializable) ratecutter);
        args.putSerializable("sm", (Serializable) sm);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

            topup = (List<SM>) getArguments().getSerializable("top_up");
            roaming = (List<SM>) getArguments().getSerializable("roaming");
            combo = (List<SM>) getArguments().getSerializable("combo");
            ratecutter = (List<SM>) getArguments().getSerializable("cutter");
            sm = (List<SM>) getArguments().getSerializable("sm");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_mobile_recharge_plans_home, container, false);

         plansTabs = view.findViewById(R.id.recharge_plan_tabs);
         plansPager = view.findViewById(R.id.plans_pager);
         viewPagerAdapter = new RechargePlansPagerAdapter(getChildFragmentManager(),0,topup,roaming,combo,ratecutter,sm);
        plansPager.setAdapter(viewPagerAdapter);
        plansTabs.setupWithViewPager(plansPager);

        return view;
    }
}
