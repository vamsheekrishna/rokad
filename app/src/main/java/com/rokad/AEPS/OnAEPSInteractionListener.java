package com.rokad.AEPS;

import com.rokad.dmt.pojos.TransactionProcessPOJO;

interface OnAEPSInteractionListener {
    void showWebView();

    void showPaymentReceipt();

    void goToCashWithdrawalView();

    void goToBalanceEnquire();

    void goToReceiptFragment();
}
