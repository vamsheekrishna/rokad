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
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatSpinner;

import com.mantra.mfs100.FingerData;
import com.mantra.mfs100.MFS100;
import com.mantra.mfs100.MFS100Event;
import com.rokad.R;
import com.rokad.dmt.pojos.TransactionProcessPOJO;
import com.rokad.model.UserData;
import com.rokad.rokad_api.RetrofitClientInstance;
import com.rokad.rokad_api.endpoints.AEPSService;
import com.rokad.rokad_api.endpoints.pojos.AEPSBank;
import com.rokad.rokad_api.endpoints.pojos.AEPSGetBankListResponse;
import com.rokad.utilities.Utils;
import com.rokad.utilities.views.BaseFragment;
import com.rokad.utilities.views.EditTextWithTitleAndThumbIcon;

import java.net.SocketTimeoutException;
import java.nio.charset.Charset;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.rokad.AEPS.AEPSActivity.BALANCE_ENQUIRY;
import static com.rokad.AEPS.AEPSActivity.CASH_WITHDRAWL;

public class CashWithdrawalFragment extends BaseFragment implements View.OnClickListener, MFS100Event {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String transCode;
    private String mParam2;
    private EditTextWithTitleAndThumbIcon firstName, lastName, mobileNumber, amt, aadhaarNumber;
    private AEPSService aepsService;
    private OnAEPSInteractionListener mListener;
    private FingerData lastCapFingerData = null;


    MFS100 mfs100 = null;
    private String xmlBiometricString = //"<?xml version=\\\"1.0\\\"?><PidData> <Resp errCode=\\\"0\\\" errInfo=\\\"Success\\\" fCount=\\\"1\\\" fType=\\\"0\\\" nmPoints=\\\"23\\\" qScore=\\\"67\\\" />  <DeviceInfo dpId=\\\"MANTRA.MSIPL\\\" rdsId=\\\"MANTRA.WIN.001\\\" rdsVer=\\\"1.0.0\\\" mi=\\\"MFS100\\\" mc=\\\"MIIEGzCCAwOgAwIBAgIIBnxV+8NqjXQwDQYJKoZIhvcNAQELBQAwgekxKjAoBgNVBAMTIURTIE1hbnRyYSBTb2Z0ZWNoIEluZGlhIFB2dCBMdGQgNTFNMEsGA1UEMxNEQiAyMDMgU2hhcGF0aCBIZXhhIG9wcG9zaXRlIEd1amFyYXQgSGlnaCBDb3VydCBTIEcgSGlnaHdheSBBaG1lZGFiYWQxEjAQBgNVBAkTCUFobWVkYWJhZDEQMA4GA1UECBMHR3VqYXJhdDESMBAGA1UECxMJVGVjaG5pY2FsMSUwIwYDVQQKExxNYW50cmEgU29mdGVjaCBJbmRpYSBQdnQgTHRkMQswCQYDVQQGEwJJTjAeFw0xOTA2MjYxNzQ2NDlaFw0xOTA3MjYxODAxMzZaMIGwMSQwIgYJKoZIhvcNAQkBFhVzdXBwb3J0QG1hbnRyYXRlYy5jb20xCzAJBgNVBAYTAklOMRAwDgYDVQQIEwdHVUpBUkFUMRIwEAYDVQQHEwlBSE1FREFCQUQxDjAMBgNVBAoTBU1TSVBMMR4wHAYDVQQLExVCaW9tZXRyaWMgTWFudWZhY3R1cmUxJTAjBgNVBAMTHE1hbnRyYSBTb2Z0ZWNoIEluZGlhIFB2dCBMdGQwggEiMA0GCSqGSIb3DQEBAQUAA4IBDwAwggEKAoIBAQDVlMA+ad7BK6GX71WzuaW+TpRWknp/oaVKfuRppxkvQjuzop0iIC9UtsGqzftROdufe4GIeCGkWry7JUqOu5TBr5g3hMwn8D8t//S7DEYUqKjIeJT21ddxBbmkPWAGULhiM2VtA5e82IUa8n3Sj9X2Fy1K5Ymiz3kjRj7Y2tUDFjdjoovnBraETI1nHifT7siY8C5hsgqggHGHBadRxtoUJL0x5ksaF1lztpBLK7v/MAxmSBApc7YeKsRarliP/zuG5IkILrt3Ucsimv2RhiKQxrUY4+uptIrpLux3P5+1lUshmBwvKIn+mJJIyrXS+wcuP2IhgLqwdziUkdtTLeApAgMBAAEwDQYJKoZIhvcNAQELBQADggEBAG8x3KBDxszJ/Fpz1Zig7oEnN/b62h2dUM80BmL0sGXPeOKKTUCosws/OoLUj09apjZ1PUWEhQh4rIx/pQ1ug1bxHFgEbHjfxBWenN22pbjSQqqqFNceG4ajVTe1cp2NMwSviRLSTAfMMEKz5EHsRBsugqWztPGXSVMBHV0TifJ4ZeOykaVO/hNVkXoKGMlp3+aW8yJYqc7vWumtyvFP3MyAgYy5kwx+JiPpZvewAo0GAbEl5rce3QxAoR7Apivsnj0IwBOuPIWIk03/TbceJFpNZ9AQvTprfooZiWK5UGtP160rxAYv/HOFams/pJ00p4Lg+KId5FxUy8Ol+Utr9ig=\\\" dc=\\\"0d293cb7-d1ff-4f01-a51f-9c8f799c2c94\\\">    <additional_info>      <Param name=\\\"srno\\\" value=\\\"294294\\\" />      <Param name=\\\"sysid\\\" value=\\\"651FEBF7A0EB9EEFBFF0\\\" />      <Param name=\\\"ts\\\" value=\\\"2019-07-05T00:17:34+05:30\\\" />    <\\/additional_info>  <\\/DeviceInfo>  <Skey ci=\\\"20191230\\\">JESFVxmd+pmhLYqFrbtDUYpXRtlcEq8naxk9Uxxm9F3adiIlOiDWuKRedpN03papEw8YyJFo24URuiYzNk4McOuWq7MknBBVs/la6eG6RRTlquZOOfKDQN9J0zTkCwOU+cXfjTfQuo16BqZrobnVn4cKFDXdjCrKQbNrMttBpOjnN8HC1A2t/IlrIUMN0Cci/WXP5p97XMrPoKeXN5XYm7e/bKoitE2JWkhIUQdFzbaw9v5TWE9s5E4PlrMBYjbO8I9Vqoaamu+4/X6ypObwP/zRQfqS9uwQdLyk29kS7ektXxHrL0GNcbnBxJiqPFcIKDWUrfvL5s2H+o4UWWgHyw==<\\/Skey>  <Hmac>tFYdTZA7asw3Y4X8JWBV3V1NaWM9PnUZwV67oF9t/pd0ghJus+pMl4ERXD5rw4NT<\\/Hmac>  <Data type=\\\"P\\\">MjAxOS0wNy0wNVQwMDoxNzozNJX6U4A17jnWemsRb1o1yxoZRb+ZpPtS/qlBIxPPu3LAZ+Z6WSbdXh6+rZqYUI/K0rJQ+SyrpqH57qaE5sEXqOpX78I+Wada3vD2xkGWUwtAxNx2ldFzocTb4MFAZSF7525dO8RvU86IlzfWhJqdVohBGdH/a3sqcH+teIXHhxC/vEOsbhVj8Ah3wZAQLgCeNQfhKrcEiBeJSlFBrfWP6onNtiFSEioa7QyG8MhOn5UCw4aBX1vjbV5c0SrjmUPpEbKRgFE3AYXYBJlGAq3EjyBakAC8tMDu/RyyOhdy4l3+QUxRhEGySQ86XNE467I530zxbeChEVYIj2XDFYNlrd1KxJHU/L7p70DaxR/p8OckzjSPmPCZ2b6WZdh8+b8Qgv2Bu8PPYXmRq3ZaJy6vDckUnkawWRNf+AY0IVFK0y225vBHqzKifxOrdJJsSD/wjrY6N62Vuf208b/tu9My7zlpZF8Bv1O0xHgfj6LIYZ2/cgiE7msNk1Q0Dy2+c4jg+fwOEMkSAetx3bri89HotHYi5WRnwaHM3zuNBDfUyAzfhC5Sjso+OdnkjZ7/sKDL24MIIiy+BlO1k0PT4PD31eagvV7AynECbfNvBawPwq+EC3dV3p0X1yvwpZ4X+hiK7fGkn2Zfb85idRzIaHzErUgy8vO/rHHV/bZi6284TWSu2q3LVQZxbG8rmrLamLHtTcXzg6qvt5kRl1Urv/IhFHNUzz4OrXEeiXAonP3Y9+ms1kwHC3mRyPqmfKBSN5K1HriP5LVZ5jzg7PF/OFpCYNyUwPe7id03eC5DgMo8Gse9/zPZ13BraHAJ7w==<\\/Data><\\/PidData>";
                                      "<?xml version='1.0'?><PidData>   <Data type='P'>MjAyMC0wNy0xNVQyMjozODozMhMi0t1AHKGjMq2EJ8sWVDg9mW+8uaySOOQxG2WlkAEUle6y1wUwjOWCXZVJBKdEEWzZA9rp1lGG5B4sSelIutHvZ2lYl96QXnbuDFF4M1H6S4z9HLK4ja80p9FdU4hHmYkWYgpwlnl95BDupeyhNXwvlloUIxKsiXA3WNKq3V2X+HrxJMz0njL/FEANfzw+rE7TkuYq7Vw6+Wk/1As/wsNtC9iyYj4OAp1Zah5x0cK0aI05qx6w/mXwzJhnMKlgqevQN1C5xjljgSIek6Cfk1GZfcYfAWVUaQtbR83p4JDYEMHFjef2jVX490L4l8fM/3VBkyTryllMZ8IZttoJ3vY3N4sUCOaLwEbUHxJEL49i0gi0WTWoNW+c1G2EbNKt6eh7HR5kzzvuFzfPkWHWV1itNjJauCP7nQV/4hPGA2jhCf2XiixB/qZOSE3XiCBdA1fLn5VnyQNxnzo0Lvl0FEF/aGy3cELFmDvw5ssA45/OM8hS2lvm9LAVopvCaHiIK+A9LigtZjWxgGCzXEoHkA22lRFyd0X822UHrR6aSswq/CuminOBm659f6SqPHTZ//z28NZMM8KSc4fdypG59tLJt1zNJ4uF8Mz/2hkHXmaHbiQSzRS9GLwGyB7jtdHUo73fk3cYvs3hSdRCjiouq5jhcOvw2nsqVdY8IyKjF3J483v473WLRDpDdjl2Aqy2eAXfn611lO38ENXcEOBFxxe9568l8Ubng1dhc/fOf9qKKxKDpzt6KLRE7mnLXGCvyLACsER37ILiUKAq8IjL/NubqelSQsbKHXdLlwMehEvZecJSua2DhoRl6YGr5el7azUmHiD2g0O8RZH6/vNFsAxsyzyf7DSACBIiTqCe33m2ex7WSE94Y9DwB5WERwB/9FfoNHMeYygpkwpN+80gr2IxSWAD96Gmpc4Es+x8NAgpcThzFavjGSR8rTkEapzNcIkvnFjvxiIr5+DkH5Z/Hhd3LYl2QRO8CDPpr+D9xXOSb8ROV8OELLVXi0+icY8UV2ZWv/jm6vjvMMQttq6tS+p6eI1t6nuVGcI4c36xKvrllnMbOsdXf9gKJfgWNGrGeg==</Data>   <DeviceInfo dc='472a67ab-de6f-44c8-bda3-6a29e5c4c1ba' dpId='MANTRA.MSIPL' mc='MIIEGTCCAwGgAwIBAgIGAXLLOldRMA0GCSqGSIb3DQEBCwUAMIHpMSowKAYDVQQDEyFEUyBNYW50cmEgU29mdGVjaCBJbmRpYSBQdnQgTHRkIDUxTTBLBgNVBDMTREIgMjAzIFNoYXBhdGggSGV4YSBvcHBvc2l0ZSBHdWphcmF0IEhpZ2ggQ291cnQgUyBHIEhpZ2h3YXkgQWhtZWRhYmFkMRIwEAYDVQQJEwlBaG1lZGFiYWQxEDAOBgNVBAgTB0d1amFyYXQxEjAQBgNVBAsTCVRlY2huaWNhbDElMCMGA1UEChMcTWFudHJhIFNvZnRlY2ggSW5kaWEgUHZ0IEx0ZDELMAkGA1UEBhMCSU4wHhcNMjAwNzE1MTY1MzEwWhcNMjAwNzE5MDYxODE3WjCBsDElMCMGA1UEAxMcTWFudHJhIFNvZnRlY2ggSW5kaWEgUHZ0IEx0ZDEeMBwGA1UECxMVQmlvbWV0cmljIE1hbnVmYWN0dXJlMQ4wDAYDVQQKEwVNU0lQTDESMBAGA1UEBxMJQUhNRURBQkFEMRAwDgYDVQQIEwdHVUpBUkFUMQswCQYDVQQGEwJJTjEkMCIGCSqGSIb3DQEJARYVc3VwcG9ydEBtYW50cmF0ZWMuY29tMIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAm9BVetCVJgMkEUwYnSr3kI93vIadLyTMnA7lFCXdJxRRpYS48Zj/Jf+3EbzsFJfq22eDxbF6pl222GJpOFnwuNMSc4Kjo014zn2c0qJKv89h5u86lDp3Qjtyix9+krjkljsrXlUtfbC9VC/aMWi/MOFr9AJ1CSJF2eCPEtOCo+Q1Sps54iwYKOOwgCTkhkf+DwWi3xajBsXkQmC0atNaG6oDC+V8UtlQwdHT5JhwsBN3lt4OVNqyxFkeT+RZNAP8bL5AonWvq+rLoVwTsVL4/bsJ+L6HUMwtCFiF5ilnKNM5lNcGkjbJ2TdTRzjS7TQQkABrsPkVYh++n/6LZo1aywIDAQABMA0GCSqGSIb3DQEBCwUAA4IBAQBXFLx+uRcVy2Z9EM9EZAKMNNHaCfjPhG0vXIH1FdLxKuyBIuQ1JX5ImLIUbC0k8gYrRvP+z2+O8lUVQozMedv9Yd20+UkN2JHq/dgwD37c+8KE9xx/LZ7QSGGhfiy7kGTDKQUY8WjXApI0tcx1ovP25cHA7e21kAE03AQlLBszTDb+QHJGkcIFreWdNolf+vGzA0ytmH0EYoEd5uQxK9Lr68eLvWqKJJGfxUWx/4AMHYo/EMzTFmQSXchtBkRSLfduQ/IBflXxN+ROokrXctZ1cEapnmAj49mmkiPnD3XC4xmSGsTtY88jzvDW809hT74gSrXxILoBwp2pOE7qsiHV' mi='MFS100' rdsId='MANTRA.AND.001' rdsVer='1.0.4'>      <additional_info>         <Param name='srno' value='3183636'/>         <Param name='sysid' value='359906070757849'/>         <Param name='ts' value='2020-07-15T22:38:38+05:30'/>      </additional_info>   </DeviceInfo>   <Hmac>ldjrFwxK6Gp0d+bJ1A9tHn0X4FmDSu63GLkX7+cl5tgd9RcSKGPyyOSuPsowRjLN</Hmac>   <Resp errCode='0' errInfo='Capture Success' fCount='1' fType='0' iCount='0' iType='0' nmPoints='56' pCount='0' pType='0' qScore='88'/>   <Skey ci='20221021'>hkqNup/lei6Jatn6+n6lXcbm/QV5EidYHZI/GIROnnH47tdhNf1oy0xpuAqoNeHQoB7fEZClyZr7yx06xX69lp2EeOkgowDwDVpSQntdidmlo3kdUxBWXf6+sHETKjco+udm00BBDWVfLi2dxCFEe5lXe5SJ88QabQJ3QOaW/5A82I7svS6uis0UKZbfsydDi8eFH7jOHYE5eChM4v7Eo+dxDHaXyTtgH8GePFgb9iP0nZ0MaUHDXO+UEqpDx6o/V+rhQKdblyqjtCvN1weV38DPDiIq0guV7NXYYiMMHo4EuYZhOfTZXyi397f0W7my/7vBAzewkpk8xh/lhJaSIg==</Skey></PidData>";
    // private String xmlBiometricString ="";
    private ArrayList<AEPSBank> bankList;
    private AEPSBank selectedBank;
    private ImageView captureImage;
    private Button submit;

    private enum ScannerAction {
        Capture, Verify
    }
    ScannerAction scannerAction = ScannerAction.Capture;
    public CashWithdrawalFragment() {
        // Required empty public constructor
    }

    public static CashWithdrawalFragment newInstance(String param1, String param2) {
        CashWithdrawalFragment fragment = new CashWithdrawalFragment();
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
            transCode = getArguments().getString(ARG_PARAM1);
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
        return inflater.inflate(R.layout.fragment_cash_withdrawal, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        submit = view.findViewById(R.id.submit);
        submit.setOnClickListener(this);
        view.findViewById(R.id.capture_button).setOnClickListener(this);
        firstName = view.findViewById(R.id.cust_fst_name);
        lastName = view.findViewById(R.id.cust_lst_name);
        captureImage = view.findViewById(R.id.capture_image);
        mobileNumber = view.findViewById(R.id.cust_mobile_num);
        amt =  view.findViewById(R.id.cust_amt);
        aadhaarNumber = view.findViewById(R.id.aadhaar_number);


        AppCompatSpinner banksListSpinner = view.findViewById(R.id.bank_list_spinner);
        banksListSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedBank = bankList.get(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        progressBar.setCancelable(false);
        progressBar.show();
        RetrofitClientInstance.getRetrofitInstance().create(AEPSService.class).getBankList().enqueue(new Callback<AEPSGetBankListResponse>() {
            @Override
            public void onResponse(Call<AEPSGetBankListResponse> call, Response<AEPSGetBankListResponse> response) {
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    bankList = response.body().getAepsBanks();
                    banksListSpinner.setAdapter(new BankListAdapter(bankList));
                } else {
                    Toast.makeText(requireActivity(), ""+response.message(),Toast.LENGTH_LONG).show();
                }
                progressBar.cancel();
            }

            @Override
            public void onFailure(Call<AEPSGetBankListResponse> call, Throwable t) {
                progressBar.cancel();
                if(t instanceof SocketTimeoutException){
                    showDialog(getString(R.string.time_out_title), getString(R.string.time_out_msg));
                } else {
                    showDialog("", t.getMessage());
                }

            }
        });

        mobileNumber.accessEditText().setInputType(InputType.TYPE_CLASS_PHONE);
        mobileNumber.accessEditText().setFilters(new InputFilter[] { new InputFilter.LengthFilter(10) });
        amt.accessEditText().setInputType(InputType.TYPE_CLASS_NUMBER);
        amt.accessEditText().setFilters(new InputFilter[] { new InputFilter.LengthFilter(5) });
        aadhaarNumber.accessEditText().setInputType(InputType.TYPE_CLASS_NUMBER);
        aadhaarNumber.accessEditText().setFilters(new InputFilter[] { new InputFilter.LengthFilter(12) });

        if(transCode.equalsIgnoreCase(BALANCE_ENQUIRY)) {
            amt.setVisibility(View.GONE);
            submit.setText(R.string.balance_enquire);
        } else {
            submit.setText(R.string.cash_withdrawal);
        }
    }

    @Override
    public void onClick(View view) {

        if(view.getId() == R.id.submit) {
            String _firstName = firstName.accessEditText().getText().toString();
            String _lastName = lastName.accessEditText().getText().toString();
            String _mobileNumber = mobileNumber.accessEditText().getText().toString();
            String _amt = amt.accessEditText().getText().toString();
            String _aadhaarNumber = aadhaarNumber.accessEditText().getText().toString();

            /*String _firstName = "Vamshee";
            String _lastName = "Krishna";
            String _mobileNumber = "7416226233";
            String _amt = "100";
            String _aadhaarNumber = "675189144808";*/
            int amount = 0;
            try {
                amount = Integer.parseInt(_amt);
            } catch (Exception e) {
                Log.d("Exception: ", "Exception: " + e.toString());
            }
            if (!Utils.isValidWord(_firstName)) {
                showDialog("Sorry!!", getString(R.string.error_first_name));
            } else if (!Utils.isValidWord(_lastName)) {
                showDialog("Sorry!!", getString(R.string.erroe_last_name));
            } else if (!Utils.isValidMobile(_mobileNumber)) {
                showDialog("Sorry!!", getString(R.string.error_valid_mobile));
            }/* else if (!Utils.isValidEmail(_email)){
                showDialog("Sorry!!",getString(R.string.error_valid_email));
            }*/ else if (transCode.equalsIgnoreCase(CASH_WITHDRAWL) && (amount < 100 || amount > 10000)) {
                showDialog("Sorry!!", getString(R.string.aeps_minimum_amount_alert));
            } else if (null == selectedBank) {
                showDialog("Sorry!!", getString(R.string.aes_select_bank));
            }  else if (!Utils.isValidAadharNumber(_aadhaarNumber)) {
                showDialog("Sorry!!", getString(R.string.error_valid_aadhaar));
            }   else if (null == xmlBiometricString || xmlBiometricString.length()<10) {
                showDialog("Sorry!!", getString(R.string.erroe_capture_thumb));
            } else {
                progressBar.show();
                aepsService = RetrofitClientInstance.getRetrofitInstance().create(AEPSService.class);
                aepsService.transactionProcess( transCode, amount, selectedBank.getIin(), xmlBiometricString, UserData.getUserData().getId(), _mobileNumber, _aadhaarNumber).enqueue(new Callback<TransactionProcessPOJO>() {
                    @Override
                    public void onResponse(Call<TransactionProcessPOJO> call, Response<TransactionProcessPOJO> response) {
                        if(response.isSuccessful()) {
                            TransactionProcessPOJO data = response.body();
                            if(data.getStatus().equalsIgnoreCase("Success")) {
                                mListener.goToReceiptFragment();
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
                });
            }
        } else {
            xmlBiometricString = "";
            captureImage.setVisibility(View.GONE);
            Intent intent = new Intent();
            intent.setAction("in.gov.uidai.rdservice.fp.INFO");
            startActivityForResult(intent, 1);
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
        } catch (Exception e) {
        }
    }
    private void initScanner() {
        try {
            int ret = mfs100.Init();
            if (ret != 0) {
                showDialog("",mfs100.GetErrorMsg(ret));
            } else {
                // showDialog("","Init success");
                String info = "Serial: " + mfs100.GetDeviceInfo().SerialNo()
                        + " Make: " + mfs100.GetDeviceInfo().Make()
                        + " Model: " + mfs100.GetDeviceInfo().Model()
                        + "\nCertificate: " + mfs100.GetCertification();
                showDialog("Init success.",info);
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
                showDialog("Uninit Success","Device removed");
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
                        "<Opts env=\"P\" Dtype=\"P\" fCount=\"1\" fType=\"0\" format=\"1\" iCount=\"0\" iType=\"0\" pCount=\"0\" pType=\"0\" pidVer=\"2.0\" timeout=\"30000\"/>" +
                        "</PidOptions>";

                Intent intent2 = new Intent();

                intent2.setAction("in.gov.uidai.rdservice.fp.CAPTURE");

                intent2.putExtra("PID_OPTIONS", pidOption);

                startActivityForResult(intent2, 2);

            } else {
                showDialog("Sorry!!", "Device is not Connected or Not ready.");
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

                    // System.out.println("RESULT PID DATA: " + xmlBiometricString);
                    // showDialog("","xmlBiometricString: "+xmlBiometricString);
                    captureImage.setVisibility(View.VISIBLE);
                } catch (Exception e) {

                    e.printStackTrace();
                }
            }
        }
        if(progressBar.isShowing()) {
            progressBar.dismiss();
        }
        // Log.d("xmlBiometricString", "xmlBiometricString: "+xmlBiometricString);
    }

}
