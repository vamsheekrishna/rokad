package com.rokad.dmt.views;

import android.os.Bundle;

import com.rokad.dmt.interfaces.OnDMTInteractionListener;
import com.rokad.utilities.views.BaseActivity;

public class DMTActivity extends BaseActivity implements OnDMTInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addFragment(DMTHomeFragment.newInstance("", ""), "DMTHomeFragment", false);
    }

    @Override
    public void goToDMTHome() {
        replaceFragment(DMTHomeFragment.newInstance("", ""), "DMTHomeFragment", false);
    }

    @Override
    public void goToReBeneficiaryRegistration() {
        replaceFragment(BeneficiaryRegistrationFragment.newInstance("", ""), "BeneficiaryRegistration", true);
    }

    @Override
    public void goToSenderRegistration() {
        replaceFragment(SenderRegistrationFragment.newInstance("", ""), "SenderRegistration", true);
    }

    @Override
    public void goToDomesticFundTransfer() {
        replaceFragment(DomesticFundTransferFragment.newInstance("", ""), "DomesticFundTransfer", true);
    }

    @Override
    public void goToConformation() {
        replaceFragment(ConfirmPaymentFragment.newInstance("", ""), "ConfirmPaymentFragment", true);
    }

    @Override
    public void showCustomDialog() {
        CustomDia
    }
}
