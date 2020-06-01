package com.rokad.AEPS;

import android.os.Bundle;

import com.rokad.R;
import com.rokad.utilities.views.ServicesBaseActivity;
import com.rokad.dmt.pojos.TransactionProcessPOJO;


public class AEPSActivity extends ServicesBaseActivity  implements OnAEPSInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_root_view);
        addFragment(new AEPSHomeFragment(),"AEPSHomeFragment", false);

    }

    @Override
    public void showWebView(TransactionProcessPOJO.TransactionProcessData data) {
        replaceFragment(WebViewFragment.newInstance(data,""), "web-view", true);
    }

    @Override
    public void showPaymentReceipt() {
        replaceFragment(ReceiptFragment.newInstance("",""), "receipt_fragment", true);
    }
}
