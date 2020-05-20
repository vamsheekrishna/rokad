package com.rokad.home;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rokad.R;
import com.rokad.authentication.LoginActivity;
import com.rokad.authentication.UserData;
import com.rokad.dmt.views.DMTActivity;
import com.rokad.home.dummy.DummyContent;
import com.rokad.mobile_recharge.views.MobileRechargeActivity;
import com.rokad.rokad_api.RetrofitClientInstance;
import com.rokad.rokad_api.endpoints.AuthenticationService;
import com.rokad.rokad_api.endpoints.pojos.ResponseWalletBalance;
import com.rokad.utilities.views.BaseFragment;

import java.net.SocketTimeoutException;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ServicesHomeFragment extends BaseFragment implements View.OnClickListener {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    TextView mBalance;
    private String mParam1;
    private String mParam2;

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
        requireActivity().setTitle("Services Home");

        updateWalletBalance();
        String walletBalance = UserData.getInstance().getWalletBalance();
        if (walletBalance == null)
            startActivity(new Intent(getContext(), LoginActivity.class));
    }

    private void updateWalletBalance() {
        progressBar.show();
        AuthenticationService authenticationService = RetrofitClientInstance.getRetrofitInstance().create(AuthenticationService.class);
        Call<ResponseWalletBalance> apiResponse = authenticationService.getWalletBalance(UserData.getInstance().getId());
        apiResponse.enqueue(new Callback<ResponseWalletBalance>() {
            @Override
            public void onResponse(Call<ResponseWalletBalance> call, Response<ResponseWalletBalance> response) {
                try {
                    if (response.body().getStatus().equals("success")) {
                        String walletBalance = response.body().getAmount(); // UserData.getInstance().getWalletBalance();
                        if (walletBalance != null) {
                            UserData.getInstance().setWalletBalance(walletBalance);
                            mBalance.setText(walletBalance);
                        }
                        else {
                           startActivity(new Intent(getContext(), LoginActivity.class));
                        }
                    } else {

                    }
                } catch (Exception e) {
                    showDialog("Sorry..!!", getString(R.string.server_failed_case));
                }
                progressBar.cancel();
            }

            @Override
            public void onFailure(Call<ResponseWalletBalance> call, Throwable t) {
                if(t instanceof SocketTimeoutException){
                    showDialog(getString(R.string.time_out_title), getString(R.string.time_out_msg));
                } else {
                    showDialog("Sorry..!!", getString(R.string.server_failed_case));
                }
                progressBar.cancel();
            }
        });
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
        view.findViewById(R.id.refresh).setOnClickListener(this);

        String usrName = UserData.getInstance().getFirstName() +" "+ UserData.getInstance().getLastName();

        if (!usrName.isEmpty() || usrName != null)
        ((TextView)view.findViewById(R.id.name)).setText(usrName);
        else
            startActivity(new Intent(getContext(),LoginActivity.class));
        mBalance = view.findViewById(R.id.balance);
        /*int mColumnCount = 3;
        if (mColumnCount <= 1) {
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        } else {
            recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), mColumnCount));
        }*/

        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        DummyContent.ITEMS.clear();
        DummyContent.createDummyItem(1, R.drawable.mobile_home_icon, "Mobile");
        DummyContent.createDummyItem(2, R.drawable.domestic_money_transfer, "Domestic Money Transfer");
        /*DummyContent.createDummyItem(2, R.drawable.advance_ticket_booking, "Advance Ticket Booking");
        DummyContent.createDummyItem(3, R.drawable.insurance, "Insurance");
        DummyContent.createDummyItem(4, R.drawable.current_bus_booking, "Current Bus Booking");
        DummyContent.createDummyItem(5, R.drawable.air_hotel_booking, "Hotel Booking");
        DummyContent.createDummyItem(6, R.drawable.car_rental, "Car Rental");
        DummyContent.createDummyItem(7, R.drawable.cash_cards, "Cash Cards");
        DummyContent.createDummyItem(8, R.drawable.dth, "DTH");
        DummyContent.createDummyItem(10, R.drawable.e_paylater, "e-Pay Later");
        DummyContent.createDummyItem(11, R.drawable.electricity, "Electricity");
        DummyContent.createDummyItem(12, R.drawable.gold_loan, "Gold Loan");*/
        recyclerView.setAdapter(new MyServiceRecyclerViewAdapter(DummyContent.ITEMS, this));
    }

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.addMoney) {
            showDialog();
        } else if(view.getId() == R.id.refresh) {
            updateWalletBalance();
        } else {
            DummyContent.DummyItem mItem = (DummyContent.DummyItem) view.getTag();
            Intent intent;
            if(mItem.id == 1) {
                intent = new Intent(getActivity(), MobileRechargeActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            } else if(mItem.id == 2) {
                intent = new Intent(getActivity(), DMTActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            } else {
                showDialog();
            }
        }
    }

    private void showDialog() {
        AlertDialog.Builder builder =new AlertDialog.Builder(requireActivity());
        builder.setTitle("Sorry....");
        builder.setMessage(R.string.feature_availability_msg);
        builder.setNegativeButton("close", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
