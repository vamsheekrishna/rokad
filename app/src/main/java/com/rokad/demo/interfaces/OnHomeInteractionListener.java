package com.rokad.demo.interfaces;

import com.rokad.demo.fragments.dummy.DummyContent;

public interface OnHomeInteractionListener {
    void goToHomeFragment();
    void goToServicesFragment();
    void goToAboutFragment();
    void goToTermsFragment();
    void goToSignOut();
    void onSelectedServiceInteraction(DummyContent.DummyItem mItem);
}
