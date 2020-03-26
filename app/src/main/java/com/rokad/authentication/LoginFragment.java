package com.rokad.authentication;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rokad.R;
import com.rokad.utilities.views.BaseFragment;

import java.util.Objects;

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
    @Override
    public void onResume() {
        super.onResume();
        Objects.requireNonNull(getActivity()).setTitle("Login");
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.user_name:
                String usrName = userName.getText().toString();
                navController.navigate(R.id.action_loginFragment_to_homeActivity);
                break;
            case R.id.password:
                String pwd = password.getText().toString();
                navController.navigate(R.id.action_loginFragment_to_registrationFragment);
                break;
            case R.id.login_button:
                /*TODO: Navigate to Home Screen upon Successful login */
//                 navController.popBackStack(R.id.action_loginFragment_to_forgotFragment, false);
                navController.navigate(R.id.action_loginFragment_to_forgotFragment);
                break;
            case R.id.forgot_pwd:
                //TODO: Display the sequence of Forgot Password Dialog screens
                break;
            case R.id.register:
                //TODO: Navigate to Register/Signup Screen
                break;
        }
    }
}
