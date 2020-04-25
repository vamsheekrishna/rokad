package com.rokad.mobile_recharge.views;

import android.os.Bundle;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rokad.R;
import com.rokad.mobile_recharge.interfaces.RecyclerOnClickHandler;
import com.rokad.mobile_recharge.adapters.TopUpRechargePlansRecyclerAdapter;
import com.rokad.mobile_recharge.models.mPlans.TOPUP;
import com.rokad.utilities.views.BaseFragment;

import java.io.Serializable;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RechargeTopUpPlans#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RechargeTopUpPlans extends BaseFragment implements RecyclerOnClickHandler {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private List<TOPUP> topup;

    public RechargeTopUpPlans() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RechargeTopUpPlans.
     */
    // TODO: Rename and change types and number of parameters
    public static RechargeTopUpPlans newInstance(String param1, String param2) {
        RechargeTopUpPlans fragment = new RechargeTopUpPlans();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public static BaseFragment newInstance(List<TOPUP> topup) {
        RechargeTopUpPlans fragment = new RechargeTopUpPlans();
        Bundle args = new Bundle();
        args.putSerializable("topup", (Serializable) topup);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
           topup = (List<TOPUP>) getArguments().getSerializable("topup");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_recharge_top_up_plans, container, false);

        RecyclerView topupPlansList = view.findViewById(R.id.top_up_plans);

        if (topup.isEmpty()){
            topupPlansList.setVisibility(View.GONE);
            ((AppCompatTextView)view.findViewById(R.id.empty_view)).setVisibility(View.VISIBLE);
        } else {
            TopUpRechargePlansRecyclerAdapter adapter = new TopUpRechargePlansRecyclerAdapter(chosenSubscriber -> onClick(chosenSubscriber), getContext(), topup);
            topupPlansList.setAdapter(adapter);
            topupPlansList.setHasFixedSize(true);
            topupPlansList.setLayoutManager(new LinearLayoutManager(getContext()));
        }

        return view;
    }

    @Override
    public void onClick(int chosenSubscriber) {

    }
}
