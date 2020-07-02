package com.rokad.AEPS;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.InputFilter;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.mantra.mfs100.FingerData;
import com.mantra.mfs100.MFS100;
import com.mantra.mfs100.MFS100Event;
import com.rokad.R;
import com.rokad.rokad_api.endpoints.AEPSService;
import com.rokad.utilities.Utils;
import com.rokad.utilities.views.BaseFragment;
import com.rokad.utilities.views.EditTextWithTitleAndThumbIcon;

import java.nio.charset.Charset;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AEPSHomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AEPSHomeFragment extends BaseFragment implements View.OnClickListener, MFS100Event {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private EditTextWithTitleAndThumbIcon firstName, lastName, mobileNumber, email, amt;
    private AEPSService aepsService;
    private OnAEPSInteractionListener mListener;
    private FingerData lastCapFingerData = null;


    MFS100 mfs100 = null;
    private String xmlBiometricString = "";
    private enum ScannerAction {
        Capture, Verify
    }
    ScannerAction scannerAction = ScannerAction.Capture;
    public AEPSHomeFragment() {
        // Required empty public constructor
    }

    public static AEPSHomeFragment newInstance(String param1, String param2) {
        AEPSHomeFragment fragment = new AEPSHomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof OnAEPSInteractionListener) {
            mListener = (OnAEPSInteractionListener) context;
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        try {
            mfs100 = new MFS100(this);
            mfs100.SetApplicationContext(getContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_a_e_p_s_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.submit).setOnClickListener(this);
        firstName = view.findViewById(R.id.cust_fst_name);
        lastName = view.findViewById(R.id.cust_lst_name);
        mobileNumber = view.findViewById(R.id.cust_mobile_num);
        email = view.findViewById(R.id.cust_email);
        amt =  view.findViewById(R.id.cust_amt);

        mobileNumber.accessEditText().setInputType(InputType.TYPE_CLASS_PHONE);
        mobileNumber.accessEditText().setFilters(new InputFilter[] { new InputFilter.LengthFilter(10) });
        amt.accessEditText().setInputType(InputType.TYPE_CLASS_NUMBER);
        email.accessEditText().setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS | InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
    }

    @Override
    public void onClick(View view) {
        /*String _firstName = firstName.accessEditText().getText().toString();
        String _lastName = lastName.accessEditText().getText().toString();
        String _mobileNumber = mobileNumber.accessEditText().getText().toString();
        String _email = email.accessEditText().getText().toString();
        String _amt = amt.accessEditText().getText().toString();*/
        String _firstName = "vamshee";
        String _lastName = "krishna";
        String _mobileNumber = "7416226233";
        String _email = email.accessEditText().getText().toString();
        String _amt = "100";

        int amount = 0;
        try {
            amount = Integer.parseInt(_amt);
        } catch (Exception e) {
            Log.d("Exception: ", "Exception: "+e.toString());
        }
        if (!Utils.isValidWord(_firstName)){
            showDialog("Sorry!!",getString(R.string.error_first_name));
        } else if (!Utils.isValidWord(_lastName)){
            showDialog("Sorry!!",getString(R.string.erroe_last_name));
        } else if (!Utils.isValidMobile(_mobileNumber)){
            showDialog("Sorry!!",getString(R.string.error_valid_mobile));
        }/* else if (!Utils.isValidEmail(_email)){
            showDialog("Sorry!!",getString(R.string.error_valid_email));
        }*/ else if (amount<100 || amount>10000){
            showDialog("Sorry!!",getString(R.string.aeps_minimum_amount_alert));
        } else {
            progressBar.show();

            /*scannerAction = ScannerAction.Capture;
            if (!isCaptureRunning) {
                StartSyncCapture();
            }*/

            Intent intent = new Intent();
            intent.setAction("in.gov.uidai.rdservice.fp.INFO");
            startActivityForResult(intent, 1);

            /*aepsService = RetrofitClientInstance.getRetrofitInstance().create(AEPSService.class);
            aepsService.transactionProcess(_firstName, _lastName, _mobileNumber, _amt,
                    UserData.getUserData().getId()).enqueue(new Callback<TransactionProcessPOJO>() {
                @Override
                public void onResponse(Call<TransactionProcessPOJO> call, Response<TransactionProcessPOJO> response) {
                    if(response.isSuccessful()) {
                        TransactionProcessPOJO data = response.body();
                        if(data.getStatus().equalsIgnoreCase("Success")) {
                            firstName.accessEditText().setText("");
                            lastName.accessEditText().setText("");
                            mobileNumber.accessEditText().setText("");
                            amt.accessEditText().setText("");
                            mListener.showWebView(data.getData());
                            // startActivity(new Intent(requireActivity(), MFS100Test.class));
                        }
                    } else {
                        showDialog("", response.body().getMsg());
                    }
                    progressBar.cancel();
                }

                @Override
                public void onFailure(Call<TransactionProcessPOJO> call, Throwable t) {
                    showDialog("", t.getMessage());
                    progressBar.cancel();
                }
            });*/
        }
    }

    private long mLastAttTime=0l;
    private static long mLastClkTime = 0;
    private static long Threshold = 1500;
    long mLastDttTime=0l;

    @Override
    public void OnDeviceAttached(int vid, int pid, boolean hasPermission) {
        if (SystemClock.elapsedRealtime() - mLastAttTime < Threshold) {
            return;
        }
        mLastAttTime = SystemClock.elapsedRealtime();
        int ret;
        if (!hasPermission) {
            showDialog("","Permission denied");
            return;
        }
        try {
            if (vid == 1204 || vid == 11279) {
                if (pid == 34323) {
                    ret = mfs100.LoadFirmware();
                    if (ret != 0) {
                        showDialog("",mfs100.GetErrorMsg(ret));
                    } else {
                        showDialog("","Load firmware success");
                    }
                } else if (pid == 4101) {
                    String key = "Without Key";
                    ret = mfs100.Init();
                    if (ret == 0) {
                        // showDialog("", key);
                        initScanner();
                    } else {
                        showDialog("",mfs100.GetErrorMsg(ret));
                    }

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void OnDeviceDetached() {
        try {

            if (SystemClock.elapsedRealtime() - mLastDttTime < Threshold) {
                return;
            }
            mLastDttTime = SystemClock.elapsedRealtime();
            UnInitScanner();
            showDialog("","Device removed");
        } catch (Exception e) {
        }
    }
    private void initScanner() {
        try {
            int ret = mfs100.Init();
            if (ret != 0) {
                showDialog("",mfs100.GetErrorMsg(ret));
            } else {
                showDialog("","Init success");
                String info = "Serial: " + mfs100.GetDeviceInfo().SerialNo()
                        + " Make: " + mfs100.GetDeviceInfo().Make()
                        + " Model: " + mfs100.GetDeviceInfo().Model()
                        + "\nCertificate: " + mfs100.GetCertification();
                showDialog("",info);
            }
        } catch (Exception ex) {
            showDialog("","Init failed, unhandled exception");
        }
    }
    private void UnInitScanner() {
        try {
            int ret = mfs100.UnInit();
            if (ret != 0) {
                showDialog("",mfs100.GetErrorMsg(ret));
            } else {
                showDialog("","Uninit Success");
                showDialog("","Uninit Success");
                lastCapFingerData = null;
            }
        } catch (Exception e) {
            Log.e("UnInitScanner.EX", e.toString());
        }
    }
    @Override
    public void OnHostCheckFailed(String err) {
        try {
            showDialog("", err);
        } catch (Exception ignored) {
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {

            String result = data.getStringExtra("DEVICE_INFO");

            String rdService = data.getStringExtra("RD_SERVICE_INFO");

            if (rdService != null) {

                System.out.println("RD SERVICE INFO: " + rdService);
            }

            if (result != null && !rdService.contains("status=\"NOTREADY\"")) {

                String pidOption = "<PidOptions ver=\"2.0\">" +

//"<Opts env=\"P\" Dtype=\"P\" fCount=\"1\" fType=\"0\" format=\"0\" iCount=\"0\" iType=\"0\" otp=\"1234\" wadh=\"Hello\" pCount=\"0\" pType=\"0\" pidVer=\"2.0\" posh=\"UNKNOWN\" timeout=\"10000\"/>" +
                        "<Opts env=\"P\" Dtype=\"P\" fCount=\"1\" fType=\"0\" format=\"1\" iCount=\"0\" iType=\"0\" pCount=\"0\" pType=\"0\" pidVer=\"2.0\" timeout=\"30000\"/>" +

                        "</PidOptions>";

                Intent intent2 = new Intent();

                intent2.setAction("in.gov.uidai.rdservice.fp.CAPTURE");

                intent2.putExtra("PID_OPTIONS", pidOption);

                startActivityForResult(intent2, 2);

            } else {
                showDialog("", "Alert! Device not ready");
            }
        }

        if (requestCode == 2) {

            String result = data.getStringExtra("PID_DATA");

            if (result != null) {

                try {

                    xmlBiometricString = "<?xml version=\"1.0\"?>" + result;

                    xmlBiometricString = xmlBiometricString.replaceAll("\"", "'");

                    xmlBiometricString = xmlBiometricString.replaceAll("\n", "");

                    byte[] byteText = xmlBiometricString.getBytes(Charset.forName("UTF-8"));

                    xmlBiometricString = new String(byteText, "UTF-8");

                    System.out.println("RESULT PID DATA: " + xmlBiometricString);
                    showDialog("","xmlBiometricString: "+xmlBiometricString);
                } catch (Exception e) {

                    e.printStackTrace();
                }
            }
        }
        if(progressBar.isShowing()) {
            progressBar.dismiss();
        }
        Log.d("xmlBiometricString", "xmlBiometricString: "+xmlBiometricString);
    }

}
