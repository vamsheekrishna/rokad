package com.rokad.AEPS;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.rokad.R;
import com.rokad.utilities.views.BaseFragment;

public class AEPSHomeFragment extends BaseFragment implements View.OnClickListener {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    OnAEPSInteractionListener onAEPSInteractionListener;
    public AEPSHomeFragment() {
        // Required empty public constructor
    }

    public static AEPSHomeFragment newInstance(String param1, String param2) {
        AEPSHomeFragment fragment = new AEPSHomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof OnAEPSInteractionListener) {
            onAEPSInteractionListener = (OnAEPSInteractionListener) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        onAEPSInteractionListener = null;
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
        return inflater.inflate(R.layout.fragment_a_e_p_s_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.cash_withdrawal).setOnClickListener(this);
        view.findViewById(R.id.balance_enquire).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.cash_withdrawal) {
            onAEPSInteractionListener.goToCashWithdrawalView();
        } else {
            onAEPSInteractionListener.goToBalanceEnquire();
        }
    }
}