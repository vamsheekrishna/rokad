package com.rokad.demo.activitys;

import android.content.Intent;
import android.os.Bundle;

import com.rokad.demo.R;
import com.rokad.demo.fragments.ForgotPasswordFragment;
import com.rokad.demo.fragments.LoginFragment;
import com.rokad.demo.fragments.SignUpFragment;
import com.rokad.demo.interfaces.OnAuthenticationInteractionListener;

public class Authentication extends BaseActivity implements OnAuthenticationInteractionListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        setTitle("Login");
        addFragment(LoginFragment.newInstance("", ""), "LoginFragment", false);
    }

    @Override
    public void goToLoginFragment() {
        setTitle("Login");
        replaceFragment(LoginFragment.newInstance("", ""), "LoginFragment", true);
    }

    @Override
    public void goToForgotPasswordFragment() {
        setTitle("Forgot Password");
        replaceFragment(ForgotPasswordFragment.newInstance("", ""), "ForgotPasswordFragment", true);
    }

    @Override
    public void goToSignUpFragment() {
        setTitle("Registration");
        replaceFragment(SignUpFragment.newInstance("", ""), "SignUp", true);
    }

    @Override
    public void goToHomeFragment() {
        startActivity(new Intent(Authentication.this,HomeActivity.class));
        finish();
    }
}
