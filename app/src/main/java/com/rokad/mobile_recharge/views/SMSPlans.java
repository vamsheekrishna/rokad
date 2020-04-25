package com.rokad.mobile_recharge.views;

import android.os.Bundle;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rokad.R;
import com.rokad.mobile_recharge.interfaces.RecyclerOnClickHandler;
import com.rokad.mobile_recharge.adapters.SMSPlansAdapter;
import com.rokad.mobile_recharge.models.mPlans.SM;
import com.rokad.utilities.views.BaseFragment;

import java.io.Serializable;
import java.util.List;

public class SMSPlans extends BaseFragment implements RecyclerOnClickHandler {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private List<SM> sm;
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

    public static BaseFragment newInstance(List<SM> sm) {
        SMSPlans fragment = new SMSPlans();
        Bundle args = new Bundle();
        args.putSerializable("sm", (Serializable) sm);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
          sm = (List<SM>) getArguments().getSerializable("sm");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_s_m_s_plans, container, false);

        RecyclerView smsPlansList = view.findViewById(R.id.sms_plans);

        if (sm.isEmpty()){
            smsPlansList.setVisibility(View.GONE);
            ((AppCompatTextView)view.findViewById(R.id.empty_view)).setVisibility(View.VISIBLE);
        } else {
            SMSPlansAdapter adapter = new SMSPlansAdapter(chosenSubscriber -> onClick(chosenSubscriber), getContext(), sm);
            smsPlansList.setAdapter(adapter);
        }

        return view;
    }

    @Override
    public void onClick(int chosenSubscriber) {

    }
}
