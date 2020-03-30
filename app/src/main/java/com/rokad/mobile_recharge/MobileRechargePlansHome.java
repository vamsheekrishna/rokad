package com.rokad.mobile_recharge;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;
import com.rokad.R;
import com.rokad.utilities.views.BaseFragment;

import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MobileRechargePlansHome#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MobileRechargePlansHome extends BaseFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private TabLayout plansTabs;
    private ViewPager plansPager;
    private RechargePlansPagerAdapter viewPagerAdapter;

    public MobileRechargePlansHome() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MobileRechargePlansHome.
     */
    // TODO: Rename and change types and number of parameters
    public static MobileRechargePlansHome newInstance(String param1, String param2) {
        MobileRechargePlansHome fragment = new MobileRechargePlansHome();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_mobile_recharge_plans_home, container, false);

         plansTabs = view.findViewById(R.id.recharge_plan_tabs);
         plansPager = view.findViewById(R.id.plans_pager);
         viewPagerAdapter =
                new RechargePlansPagerAdapter(getChildFragmentManager(),0);
        plansPager.setAdapter(viewPagerAdapter);
        plansTabs.setupWithViewPager(plansPager);

        return view;
    }
}
