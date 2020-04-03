package com.rokad.mobile_recharge.views;

import android.os.Bundle;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rokad.R;
import com.rokad.mobile_recharge.interfaces.RecyclerOnClickHandler;
import com.rokad.mobile_recharge.adapters.SMSPlansAdapter;
import com.rokad.utilities.views.BaseFragment;

public class SMSPlans extends BaseFragment implements RecyclerOnClickHandler {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public SMSPlans() {
        // Required empty public constructor
    }

    public static SMSPlans newInstance(String param1, String param2) {
        SMSPlans fragment = new SMSPlans();
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
        View view = inflater.inflate(R.layout.fragment_s_m_s_plans, container, false);

        RecyclerView smsPlansList = view.findViewById(R.id.sms_plans);
        SMSPlansAdapter adapter = new SMSPlansAdapter(chosenSubscriber -> onClick(chosenSubscriber), getContext());
        smsPlansList.setAdapter(adapter);

        return view;
    }

    @Override
    public void onClick(int chosenSubscriber) {

    }
}