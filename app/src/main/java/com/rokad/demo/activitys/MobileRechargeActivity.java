package com.rokad.demo.activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.rokad.demo.R;
import com.rokad.demo.fragments.BaseFragment;
import com.rokad.demo.fragments.MobileRechargeFragment;

public class MobileRechargeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        addFragment(MobileRechargeFragment.newInstance(" ", " "), "MobileRechargeFragment", false);
    }
}
