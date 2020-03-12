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
        addFragment(LoginFragment.newInstance("", ""), "LoginFragment", true);
    }

    @Override
    public void goToLoginFragment() {
        replaceFragment(LoginFragment.newInstance("", ""), "LoginFragment", true);
    }

    @Override
    public void goToForgotPasswordFragment() {
        replaceFragment(ForgotPasswordFragment.newInstance("", ""), "ForgotPasswordFragment", true);
    }

    @Override
    public void goToSignUpFragment() {
        replaceFragment(SignUpFragment.newInstance("", ""), "ForgotPasswordFragment", true);
    }

    @Override
    public void goToHomeFragment() {
        startActivity(new Intent(Authentication.this,HomeActivity.class));
        finish();
    }
}
