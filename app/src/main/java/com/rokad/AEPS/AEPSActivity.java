package com.rokad.AEPS;

import android.content.Intent;
import android.os.Bundle;

import com.rokad.R;
import com.rokad.dmt.views.DMTActivity;
import com.rokad.utilities.views.ServicesBaseActivity;


public class AEPSActivity extends ServicesBaseActivity  implements OnAEPSInteractionListener {

    public static final String CASH_WITHDRAWL = "CW";
    public static final String BALANCE_ENQUIRY = "BE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_root_view);
        addFragment(AEPSHomeFragment.newInstance("", ""),"AEPSHomeFragment", false);
    }

    @Override
    public void showWebView() {
        replaceFragment(WebViewFragment.newInstance(""), "web-view", true);
    }

    @Override
    public void showPaymentReceipt() {
        replaceFragment(ReceiptFragment.newInstance("",""), "receipt_fragment", true);
    }

    @Override
    public void goToCashWithdrawalView() {
        replaceFragment(CashWithdrawalFragment.newInstance(CASH_WITHDRAWL,""),"CashWithdrawalView", true);
    }

    @Override
    public void goToBalanceEnquire() {
        replaceFragment(CashWithdrawalFragment.newInstance(BALANCE_ENQUIRY,""),"BalanceEnquire", true);
    }

    @Override
    public void goToReceiptFragment() {
        replaceFragment(ReceiptFragment.newInstance("", ""), "ReceiptFragment", true);
    }

    @Override
    public void makeAnotherPayment() {
        Intent intent = new Intent(getApplicationContext(), AEPSActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }
}
