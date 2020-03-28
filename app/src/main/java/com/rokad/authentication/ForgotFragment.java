package com.rokad.authentication;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.textfield.TextInputEditText;
import com.rokad.R;
import com.rokad.rokad_api.RetrofitClientInstance;
import com.rokad.rokad_api.endpoints.GetUserDataService;
import com.rokad.rokad_api.endpoints.pojos.ResponseForgotPassword;
import com.rokad.rokad_api.endpoints.pojos.ResponseUser;
import com.rokad.rokad_api.endpoints.pojos.User;
import com.rokad.utilities.views.BaseFragment;

import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgotFragment extends BaseFragment implements View.OnClickListener {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    TextInputEditText eMail;
    private String mParam1;
    private String mParam2;

    public ForgotFragment() {
        // Required empty public constructor
    }

    public static ForgotFragment newInstance(String param1, String param2) {
        ForgotFragment fragment = new ForgotFragment();
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
        return inflater.inflate(R.layout.fragment_forgot, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        Objects.requireNonNull(getActivity()).setTitle("Forgot Password");
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
        view.findViewById(R.id.submit).setOnClickListener(this);
        eMail = view.findViewById(R.id.email_addr_forgot_pwd);
    }

    @Override
    public void onClick(View view) {
        checkForgotPassword();
    }

    private void checkForgotPassword() {

        ProgressDialog progressBar = new ProgressDialog(getActivity(), R.style.mySpinnerTheme);
        progressBar.setCancelable(false);
        progressBar.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
        progressBar.show();

        GetUserDataService getUserDataService = RetrofitClientInstance.getRetrofitInstance().create(GetUserDataService.class);
        Call<ResponseForgotPassword> user = getUserDataService.forgotPassword(Objects.requireNonNull(eMail.getText()).toString());//("", "");
        user.enqueue(new Callback<ResponseForgotPassword>() {
            @Override
            public void onResponse(Call<ResponseForgotPassword> call, Response<ResponseForgotPassword> response) {
                Log.d("onResponse", "onResponse: ");
                showAlertDialog(response.body().getStatus(), response.body().getMsg());
                /*if(Objects.requireNonNull(response.body()).getStatus().equalsIgnoreCase("success")) {
                    List<User> users = response.body().getData();
                    User user = users.get(0);
                    UserData.setInstance(user);
                    navController.navigate(R.id.action_loginFragment_to_homeActivity);
                    getActivity().finish();
                }*/
                progressBar.dismiss();
            }

            @Override
            public void onFailure(Call<ResponseForgotPassword> call, Throwable t) {
                Log.d("onFailure", "onFailure: ");
                progressBar.dismiss();
            }
        });
    }

    private void showAlertDialog(String title, String text) {
        AlertDialog.Builder builder = new AlertDialog.Builder(Objects.requireNonNull(getActivity()));
        builder.setTitle(title.toUpperCase())
                .setMessage(text)
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }
}
