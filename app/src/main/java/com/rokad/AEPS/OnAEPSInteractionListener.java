package com.rokad.AEPS;

import com.rokad.dmt.pojos.TransactionProcessPOJO;

interface OnAEPSInteractionListener {
    void showWebView(TransactionProcessPOJO.TransactionProcessData data);
}
