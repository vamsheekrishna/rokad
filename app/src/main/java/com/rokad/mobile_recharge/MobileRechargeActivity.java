package com.rokad.mobile_recharge;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.rokad.R;
import com.rokad.utilities.views.BaseActivity;

public class MobileRechargeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobile_recharge);
        assert getSupportActionBar() != null;   //null check
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);   //show back button

    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

}
