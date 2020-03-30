package com.rokad.home;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rokad.R;
import com.rokad.authentication.UserData;
import com.rokad.home.dummy.DummyContent;
import com.rokad.mobile_recharge.MobileRechargeActivity;
import com.rokad.utilities.views.BaseFragment;

import java.util.Objects;

public class ServicesHomeFragment extends BaseFragment implements View.OnClickListener {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private int mColumnCount = 3;

    public ServicesHomeFragment() {
        // Required empty public constructor
    }

    public static ServicesHomeFragment newInstance(String param1, String param2) {
        ServicesHomeFragment fragment = new ServicesHomeFragment();
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
    public void onResume() {
        super.onResume();
        Objects.requireNonNull(getActivity()).setTitle("Services Home");
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_services_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = view.findViewById(R.id.list);
        view.findViewById(R.id.addMoney).setOnClickListener(this);
        ((TextView)view.findViewById(R.id.name)).setText(UserData.getInstance().getFirstName() +" "+ UserData.getInstance().getLastName());
        ((TextView)view.findViewById(R.id.balance)).setText(UserData.getInstance().getWalletBalance());
        if (mColumnCount <= 1) {
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        } else {
            recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), mColumnCount));
        }
        DummyContent.ITEMS.clear();
        DummyContent.createDummyItem(1, R.drawable.mobile, "Mobile");
        DummyContent.createDummyItem(2, R.drawable.advance_ticket_booking, "Advance Ticket Booking");
        DummyContent.createDummyItem(3, R.drawable.insurance, "Insurance");
        DummyContent.createDummyItem(4, R.drawable.current_bus_booking, "Current Bus Booking");
        DummyContent.createDummyItem(5, R.drawable.air_hotel_booking, "Hotel Booking");
        DummyContent.createDummyItem(6, R.drawable.car_rental, "Car Rental");
        DummyContent.createDummyItem(7, R.drawable.cash_cards, "Cash Cards");
        DummyContent.createDummyItem(8, R.drawable.dth, "DTH");
        DummyContent.createDummyItem(9, R.drawable.domestic_money_transfer, "Domestic Money Transfer");
        DummyContent.createDummyItem(10, R.drawable.e_paylater, "e-Pay Later");
        DummyContent.createDummyItem(11, R.drawable.electricity, "Electricity");
        DummyContent.createDummyItem(12, R.drawable.gold_loan, "Gold Loan");
        recyclerView.setAdapter(new MyServiceRecyclerViewAdapter(DummyContent.ITEMS, this));
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.addMoney) {
            showDialog();
        } else {
            DummyContent.DummyItem mItem = (DummyContent.DummyItem) view.getTag();
            if(mItem.id == 1) {
                startActivity(new Intent(getActivity(), MobileRechargeActivity.class));
            } else {
                showDialog();
            }
        }
    }

    private void showDialog() {
        AlertDialog.Builder builder =new AlertDialog.Builder(Objects.requireNonNull(getActivity()));
        builder.setTitle("Sorry....");
        builder.setMessage("Please wait, this feature will be available soon");
        builder.setNegativeButton("close", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
