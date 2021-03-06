package com.rokad.authentication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.navigation.Navigation;

import com.rokad.BuildConfig;
import com.rokad.R;
import com.rokad.home.HomeActivity;
import com.rokad.model.UserData;
import com.rokad.rokad_api.RetrofitClientInstance;
import com.rokad.rokad_api.endpoints.AuthenticationService;
import com.rokad.rokad_api.endpoints.pojos.ResponseUser;
import com.rokad.rokad_api.endpoints.pojos.User;
import com.rokad.utilities.Utils;
import com.rokad.utilities.views.BaseFragment;

import java.net.SocketTimeoutException;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginFragment extends BaseFragment implements View.OnClickListener {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    private AppCompatEditText userName, password;
    private AppCompatButton loginBtn;
    private AppCompatTextView forgotPwd;
    private AppCompatTextView register;

    public LoginFragment() {
        // Required empty public constructor
    }


    public static LoginFragment newInstance(String param1, String param2) {
        LoginFragment fragment = new LoginFragment();
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

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        Objects.requireNonNull(((AppCompatActivity) requireActivity()).getSupportActionBar()).hide();

         userName = view.findViewById(R.id.user_name);
         password = view.findViewById(R.id.password);
         loginBtn = view.findViewById(R.id.login_button);
         forgotPwd = view.findViewById(R.id.forgot_pwd);
         register = view.findViewById(R.id.register);

         userName.setText(BuildConfig.USERNAME);
         password.setText(BuildConfig.AUTH_PASSWORD);

        return view;
    }
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onResume() {
        super.onResume();
        requireActivity().setTitle("Login");
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);

        view.findViewById(R.id.login_button).setOnClickListener(this);
        view.findViewById(R.id.forgot_pwd).setOnClickListener(this);
        view.findViewById(R.id.register).setOnClickListener(this);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        if (userName.getText().toString() != null || !userName.getText().toString().isEmpty()){
            outState.putString("userName",userName.getText().toString());
        }

        if (password.getText().toString() != null || !password.getText().toString().isEmpty()){
            outState.putString("userName",password.getText().toString());
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.user_name:
                String usrName = userName.getText().toString();
                break;
            case R.id.password:
                String pwd = password.getText().toString();
                break;
            case R.id.login_button:
                checkLogin();
                break;
            case R.id.forgot_pwd:
                navController.navigate(R.id.action_loginFragment_to_forgotFragment);
                break;
            case R.id.register:
                navController.navigate(R.id.action_loginFragment_to_registrationFragment);
                break;
        }
    }

    private void checkLogin() {
        if (Utils.internetConnectionAvailable()) {
            String mobileNumber = userName.getText().toString();
            String _password = password.getText().toString();
            if (!Utils.isValidMobile(mobileNumber)) {
                showDialog("Sorry!!", getString(R.string.invalid_mobile_number_alert));
            } else if (_password.length() <= 4) {
                showDialog("Sorry!!", getString(R.string.invalid_password_alert));
            } else {
                ProgressDialog progressBar = new ProgressDialog(getActivity(), R.style.mySpinnerTheme);
                progressBar.setCancelable(false);
                progressBar.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
                progressBar.show();

                AuthenticationService getUserDataService = RetrofitClientInstance.getRetrofitInstance().create(AuthenticationService.class);
                Call<ResponseUser> user = getUserDataService.login(mobileNumber, _password);//("", "");
                user.enqueue(new Callback<ResponseUser>() {
                    @Override
                    public void onResponse(Call<ResponseUser> call, Response<ResponseUser> response) {
                        Log.d("onResponse", "onResponse: Login Fragment");
                        if (response.body() != null && response.body().getStatus().equalsIgnoreCase("success")) {
                            List<User> users = response.body().getData();
                            User user = users.get(0);
                            UserData.setInstance(user);
                            startActivity(new Intent(requireActivity(), HomeActivity.class));
                            // navController.navigate(R.id.action_loginFragment_to_homeActivity);
                            requireActivity().finish();
                        } else if (response.body() != null){
                            showDialog("Sorry..", response.body().getMsg());
                        } else {
                            showDialog("Sorry..","Looks like server is not available at the moment. Please make sure your Internet Connectivity is stable" +
                                    " and try again after some time.");
                        }
                        progressBar.dismiss();
                    }

                    @Override
                    public void onFailure(Call<ResponseUser> call, Throwable t) {
                        // Log.d("onFailure", "onFailure: Login Fragment ");
                        progressBar.dismiss();
                        if(t instanceof SocketTimeoutException){
                            showDialog(getString(R.string.time_out_title), getString(R.string.time_out_msg));
                        } else {
                            showDialog("Sorry..!!", getString(R.string.server_failed_case));
//                            Toast.makeText(requireActivity(), t.getMessage(), Toast.LENGTH_LONG).show();
                        }
                        // showDialog("Sorry..", getString(R.string.internet_failed_login_case));
                    }
                });

            }
        } else {
            showDialog("Sorry!!", getString(R.string.internet_check));
        }
    }
}
