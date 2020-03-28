package com.rokad.authentication;

import android.app.ProgressDialog;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rokad.R;
import com.rokad.rokad_api.endpoints.pojos.ResponseUser;
import com.rokad.rokad_api.endpoints.pojos.User;
import com.rokad.rokad_api.RetrofitClientInstance;
import com.rokad.rokad_api.endpoints.GetUserDataService;
import com.rokad.utilities.views.BaseFragment;

import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginFragment extends BaseFragment implements View.OnClickListener {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
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
        Objects.requireNonNull(((AppCompatActivity) Objects.requireNonNull(getActivity())).getSupportActionBar()).hide();

         userName = view.findViewById(R.id.user_name);
         password = view.findViewById(R.id.password);
         loginBtn = view.findViewById(R.id.login_button);
         forgotPwd = view.findViewById(R.id.forgot_pwd);
         register = view.findViewById(R.id.register);

        return view;
    }
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onResume() {
        super.onResume();
        Objects.requireNonNull(getActivity()).setTitle("Login");
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

        ProgressDialog progressBar = new ProgressDialog(getActivity(), R.style.mySpinnerTheme);
        progressBar.setCancelable(false);
        progressBar.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
        progressBar.show();

        GetUserDataService getUserDataService = RetrofitClientInstance.getRetrofitInstance().create(GetUserDataService.class);
        Call<ResponseUser> user = getUserDataService.login(userName.getText().toString(), password.getText().toString());//("", "");
        user.enqueue(new Callback<ResponseUser>() {
            @Override
            public void onResponse(Call<ResponseUser> call, Response<ResponseUser> response) {
                Log.d("onResponse", "onResponse: ");
                if(response.body().getStatus().equalsIgnoreCase("success")) {
                    List<User> users = response.body().getData();
                    User user = users.get(0);
                    UserData.setInstance(user);
                    navController.navigate(R.id.action_loginFragment_to_homeActivity);
                    getActivity().finish();
                }
                progressBar.dismiss();
            }

            @Override
            public void onFailure(Call<ResponseUser> call, Throwable t) {
                Log.d("onFailure", "onFailure: ");
                progressBar.dismiss();
            }
        });
    }
}
