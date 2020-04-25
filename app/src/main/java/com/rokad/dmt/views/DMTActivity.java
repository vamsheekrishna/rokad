package com.rokad.dmt.views;

import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.rokad.R;
import com.rokad.dmt.RequestPackets.BeneficiaryRegistrationDetails;
import com.rokad.dmt.RequestPackets.CommissionDetails;
import com.rokad.dmt.RequestPackets.DMTHomeScreenDetails;
import com.rokad.dmt.RequestPackets.FundTransferDetails;
import com.rokad.dmt.RequestPackets.NewSenderRegistrationDetails;
import com.rokad.dmt.interfaces.OnDMTInteractionListener;
import com.rokad.mobile_recharge.views.RechargeDialogFragment;
import com.rokad.utilities.views.BaseActivity;
import com.rokad.utilities.views.ServicesBaseActivity;

import java.util.Objects;

public class DMTActivity extends ServicesBaseActivity implements OnDMTInteractionListener {


    private DMTHomeScreenDetails homeScreenDetails = new DMTHomeScreenDetails();
    private FundTransferDetails fundTransferDetails = new FundTransferDetails();
    private NewSenderRegistrationDetails senderRegistrationDetails = new NewSenderRegistrationDetails();
    private BeneficiaryRegistrationDetails beneficiaryRegistrationDetails = new BeneficiaryRegistrationDetails();
    private CommissionDetails commissionDetails = new CommissionDetails();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_root_view);
        addFragment(DMTHomeFragment.newInstance("", ""), "DMTHomeFragment", false);
    }

    @Override
    public DMTHomeScreenDetails getHomeScreenDetails() {
        return homeScreenDetails;
    }

    @Override
    public FundTransferDetails getFundTransferDetails() {
        return fundTransferDetails;
    }

    @Override
    public NewSenderRegistrationDetails getSenderRegistrationDetails() {
        return senderRegistrationDetails;
    }

    @Override
    public BeneficiaryRegistrationDetails getBeneficiaryRegistrationDetails() {
        return beneficiaryRegistrationDetails;
    }

    @Override
    public CommissionDetails getCommissionDetails() {
        return commissionDetails;
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
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        Fragment prev = getSupportFragmentManager().findFragmentByTag("DMTCommissionDialogFragment");
        if (prev != null) {
            ft.remove(prev);
        }
        ft.addToBackStack(null);
        DialogFragment dialogFragment;
        dialogFragment = PaymentDialogFragment.newInstance(true, "");
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
        dialogFragment.show(ft, "DMTCommissionDialogFragment");
    }

    @Override
    public void makeAnotherPayment() {
        goToDMTHome();
    }
}
