package com.rokad.mobile_recharge.views;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rokad.R;
import com.rokad.mobile_recharge.interfaces.RecyclerOnClickHandler;
import com.rokad.mobile_recharge.adapters.ComboPlansAdapter;
import com.rokad.mobile_recharge.models.mPlans.COMBO;
import com.rokad.utilities.views.BaseFragment;

import java.io.Serializable;
import java.util.List;

public class ComboPlans extends BaseFragment implements RecyclerOnClickHandler {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private List<COMBO> combo;
    public ComboPlans() {
        // Required empty public constructor
    }


    public static BaseFragment newInstance(List<COMBO> combo) {
        ComboPlans fragment = new ComboPlans();
        Bundle args = new Bundle();
        args.putSerializable("combo", (Serializable) combo);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            combo = (List<COMBO>) getArguments().getSerializable("combo");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_combo_plans, container, false);

        RecyclerView comboPlansList = view.findViewById(R.id.combo_plans);
        ComboPlansAdapter adapter = new ComboPlansAdapter(chosenSubscriber -> onClick(chosenSubscriber), getContext(),combo);
        comboPlansList.setAdapter(adapter);
        comboPlansList.setLayoutManager(new LinearLayoutManager(getContext()));

        return view;
    }

    @Override
    public void onClick(int chosenSubscriber) {

    }
}
