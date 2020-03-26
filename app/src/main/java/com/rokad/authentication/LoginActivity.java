package com.rokad.authentication;

import android.os.Bundle;

import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.rokad.R;
import com.rokad.utilities.views.BaseActivity;

public class LoginActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }
}
