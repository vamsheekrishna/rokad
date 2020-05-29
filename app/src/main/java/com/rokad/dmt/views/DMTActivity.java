package com.rokad.dmt.views;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.rokad.R;
import com.rokad.dmt.interfaces.OnDMTInteractionListener;
import com.rokad.dmt.pojos.BeneficiaryListResponsePOJO;
import com.rokad.dmt.pojos.NewTransactionProcessResponsePOJO;
import com.rokad.dmt.pojos.SenderRegistration.SenderData;
import com.rokad.dmt.pojos.beneficiaryList.Beneficiary;
import com.rokad.dmt.pojos.beneficiaryList.Data;
import com.rokad.utilities.views.ServicesBaseActivity;

public class DMTActivity extends ServicesBaseActivity implements OnDMTInteractionListener {


//    private DMTHomeScreenDetails homeScreenDetails = new DMTHomeScreenDetails();
//    private FundTransferDetails fundTransferDetails = new FundTransferDetails();
//    private NewSenderRegistrationDetails senderRegistrationDetails = new NewSenderRegistrationDetails();
//    private BeneficiaryRegistrationDetails beneficiaryRegistrationDetails = new BeneficiaryRegistrationDetails();
//    private CommissionDetails commissionDetails = new CommissionDetails();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_root_view);
        addFragment(DMTHomeFragment.newInstance("", ""), "DMTHomeFragment", false);
    }
    @Override
    public void goToDMTHome() {
        replaceFragment(DMTHomeFragment.newInstance("", ""), "DMTHomeFragment", false);
    }

    @Override
    public void goToReBeneficiaryRegistration(Data senderData) {
        replaceFragment(BeneficiaryRegistrationFragment.newInstance(senderData, ""), "BeneficiaryRegistration", true);
    }

    @Override
    public void goToSenderRegistration() {
        replaceFragment(SenderRegistrationFragment.newInstance("", ""), "SenderRegistration", true);
    }

    @Override
    public void goToDomesticFundTransfer(BeneficiaryListResponsePOJO beneficiaryListResponsePOJO) {
        replaceFragment(DomesticFundTransferFragment.newInstance(beneficiaryListResponsePOJO, ""), "DomesticFundTransfer", true);
    }

    @Override
    public void goToConformation(com.rokad.dmt.pojos.FundTransfer.Data data, Beneficiary selectedBeneficiary) {
        replaceFragment(ConfirmPaymentFragment.newInstance(data, selectedBeneficiary), "ConfirmPaymentFragment", true);
    }

    @Override
    public void showCustomDialog(NewTransactionProcessResponsePOJO newTransactionProcessResponsePOJO) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        Fragment prev = getSupportFragmentManager().findFragmentByTag("DMTCommissionDialogFragment");
        if (prev != null) {
            ft.remove(prev);
        }
        ft.addToBackStack(null);
        DialogFragment dialogFragment;
        dialogFragment = PaymentStatusDialogFragment.newInstance(true, newTransactionProcessResponsePOJO);
        dialogFragment.setCancelable(false);
        dialogFragment.show(ft, "PaymentDialogFragment");
    }

    @Override
    public void showCommissionDialog() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        Fragment prev = getSupportFragmentManager().findFragmentByTag("DMTCommissionDialogFragment");
        if (prev != null) {
            ft.remove(prev);
        }
        ft.addToBackStack(null);
        DialogFragment dialogFragment;
        dialogFragment = DMTCommissionDialogFragment.newInstance(true);
        dialogFragment.setCancelable(false);
        dialogFragment.show(ft, "OTPVerificationDialog");
    }

    @Override
    public void showCustomOTPDialog(SenderData senderData, BeneficiaryListResponsePOJO paytmVerification) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        Fragment prev = getSupportFragmentManager().findFragmentByTag("OTPVerificationDialog");
        if (prev != null) {
            ft.remove(prev);
        }
        ft.addToBackStack("OTPVerificationDialog");
        DialogFragment dialogFragment;
        dialogFragment = OTPVerificationDialogFragment.newInstance(senderData, paytmVerification);
        dialogFragment.setCancelable(false);
        dialogFragment.show(ft, "OTPVerificationDialog");
    }

    @Override
    public void makeAnotherPayment() {
        Intent intent = new Intent(getApplicationContext(), DMTActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
        // goToDMTHome();
    }

}
