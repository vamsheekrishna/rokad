package com.rokad.dmt.views;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.rokad.BuildConfig;
import com.rokad.R;
import com.rokad.authentication.UserData;
import com.rokad.dmt.interfaces.OnDMTInteractionListener;
import com.rokad.dmt.pojos.BeneficiaryListResponsePOJO;
import com.rokad.dmt.pojos.FundTransferResponsePOJO;
import com.rokad.dmt.pojos.NewTransactionProcessResponsePOJO;
import com.rokad.dmt.pojos.beneficiaryList.Beneficiary;
import com.rokad.dmt.pojos.beneficiaryList.Data;
import com.rokad.dmt.viewmodels.SenderData;
import com.rokad.rokad_api.RetrofitClientInstance;
import com.rokad.rokad_api.endpoints.DMTModuleService;
import com.rokad.utilities.Utils;
import com.rokad.utilities.views.BaseFragment;
import com.rokad.utilities.views.EditTextWithTitleAndThumbIcon;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DomesticFundTransferFragment extends BaseFragment implements View.OnClickListener, View.OnFocusChangeListener {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private BeneficiaryListResponsePOJO mBeneficiaryListResponsePOJO;
    private String mParam2;
    private OnDMTInteractionListener mListener;
    private EditTextWithTitleAndThumbIcon senderName, transferLimit, transferAmount;
    private AppCompatSpinner beneficiariesSpinner;
    private EditTextWithTitleAndThumbIcon senderMobileNumber, senderRegID;
    private RadioGroup transferTypeGroup;
    private RadioButton transferType;
    SenderData senderData;
    private ProgressDialog progressBar;

    public DomesticFundTransferFragment() {
        // Required empty public constructor
    }

    public static DomesticFundTransferFragment newInstance(BeneficiaryListResponsePOJO param1, String param2) {
        DomesticFundTransferFragment fragment = new DomesticFundTransferFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnDMTInteractionListener) {
            mListener = (OnDMTInteractionListener) context;
            senderData = new ViewModelProvider(this).get(SenderData.class);
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mBeneficiaryListResponsePOJO = (BeneficiaryListResponsePOJO) getArguments().getSerializable(ARG_PARAM1);
            Data sender = mBeneficiaryListResponsePOJO.getData();
            senderData.setSenderData(sender);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        requireActivity().setTitle("Transfer Money");
        progressBar = new ProgressDialog(getActivity(), R.style.mySpinnerTheme);
        progressBar.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
        progressBar.setCancelable(false);
        return inflater.inflate(R.layout.fragment_domestic_fund_transfer, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();

        progressBar.show();
        RetrofitClientInstance.getRetrofitInstance().create(DMTModuleService.class).getBeneficiaryLis(
                senderData.getSenderData().getSenderMobileNo(),
                UserData.getUserData().getId(),
                BuildConfig.MOBILE_APPLICATION,
                BuildConfig.MOBILE_VERSION_ID).enqueue(new Callback<BeneficiaryListResponsePOJO>() {
            @Override
            public void onResponse(Call<BeneficiaryListResponsePOJO> call, Response<BeneficiaryListResponsePOJO> response) {
                if (response.isSuccessful()) {
                    BeneficiaryListResponsePOJO _beneficiaryListResponsePOJO = response.body();
                    if (_beneficiaryListResponsePOJO.getData().getBcSenderVerified().equalsIgnoreCase("N")) {
                        mListener.showCustomOTPDialog(null, _beneficiaryListResponsePOJO);
                    } else {
                        senderData.setSenderData(_beneficiaryListResponsePOJO.getData());
                        List<Beneficiary> list = senderData.getSenderData().getBeneficiaries().getBeneficiary();
                        if(null != list && list.size() > 0) {
                            beneficiariesSpinner.setAdapter(new BeneficiaryAdapter(senderData.getSenderData().getBeneficiaries().getBeneficiary()));
                        } else {
                            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                                    R.array.beneficiary_default_spinner_item, android.R.layout.simple_spinner_item);
                            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            beneficiariesSpinner.setAdapter(adapter);
                        }
                    }
                } else {
                    Toast.makeText(requireActivity(), response.message(), Toast.LENGTH_LONG).show();
                }
                progressBar.cancel();
            }

            @Override
            public void onFailure(Call<BeneficiaryListResponsePOJO> call, Throwable t) {
                Toast.makeText(requireActivity(), t.getMessage(), Toast.LENGTH_LONG).show();
                Log.d("Failure", "Failure: " + t.getMessage());
                progressBar.cancel();
            }
        });

        senderRegID.accessEditText().setText(senderData.getSenderData().getSenderId());
        transferLimit.accessEditText().setText(senderData.getSenderData().getImpsLimit());
        senderMobileNumber.accessEditText().setText(senderData.getSenderData().getSenderMobileNo());
        senderName.accessEditText().setText(senderData.getSenderData().getSenderName());

        if (senderData.getTransferMode().equals("NEFT")) {
            transferLimit.accessSubHeaderTextView().setText("Sender NEFT Transfer Limit");
            transferLimit.accessEditText().setText(senderData.getSenderData().getNeftLimitRs() + "");
        } else {
            transferLimit.accessSubHeaderTextView().setText("Sender IMPS Transfer Limit");
            transferLimit.accessEditText().setText(senderData.getSenderData().getImpsLimitRs() + "");
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.reg_beneficiary).setOnClickListener(this);
        view.findViewById(R.id.check_commission).setOnClickListener(this);
        view.findViewById(R.id.submit).setOnClickListener(this);

        senderMobileNumber = view.findViewById(R.id.mobile_number);
        senderMobileNumber.accessEditText().setInputType(InputType.TYPE_CLASS_PHONE);
        InputFilter[] filterArray = new InputFilter[1];
        filterArray[0] = new InputFilter.LengthFilter(10);
        senderMobileNumber.accessEditText().setFilters(filterArray);
        senderMobileNumber.accessEditText().setFocusable(false);

        senderName = view.findViewById(R.id.sender_name);
        senderName.accessSubHeaderTextView().setText("Sender Name");
        senderName.accessEditText().setFocusable(false);

        senderName.accessEditText().setOnFocusChangeListener(this);

        transferLimit = view.findViewById(R.id.transfer_limit);
        transferLimit.accessEditText().setFocusable(false);

        senderRegID = view.findViewById(R.id.sender_reg_id);
        senderRegID.accessEditText().setFocusable(false);

        transferTypeGroup = view.findViewById(R.id.transfer_type);

        transferAmount = view.findViewById(R.id.transfer_amt);
        transferAmount.accessEditText().setInputType(InputType.TYPE_CLASS_NUMBER);

        senderData.setTransferMode("IMPS");
        transferTypeGroup.setOnCheckedChangeListener((group, checkedId) -> {
            transferType = view.findViewById(checkedId);
            senderData.setTransferMode(transferType.getText().toString());
            if (senderData.getTransferMode().equals("NEFT")) {
                transferLimit.accessSubHeaderTextView().setText("Sender NEFT Transfer Limit");
                transferLimit.accessEditText().setText(senderData.getSenderData().getNeftLimitRs() + "");
            } else {
                transferLimit.accessSubHeaderTextView().setText("Sender IMPS Transfer Limit");
                transferLimit.accessEditText().setText(senderData.getSenderData().getImpsLimitRs() + "");
            }
        });

        beneficiariesSpinner = view.findViewById(R.id.spinner_view);
        beneficiariesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                try {
                    senderData.setSelectedBeneficiary(senderData.getSenderData().getBeneficiaries().getBeneficiary().get(i));
                } catch (Exception e) {

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        switch (v.getId()) {
            case R.id.sender_name:

                if (!hasFocus) {
                    String mobile = senderMobileNumber.accessEditText().getText().toString();
                    String name = senderName.accessEditText().getText().toString();
                    if (Utils.isValidMobile(mobile) && Utils.isValidWord(name)) {
                        ProgressDialog progressBar = new ProgressDialog(getActivity(), R.style.mySpinnerTheme);
                        progressBar.setCancelable(false);
                        progressBar.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
                        progressBar.show();
                        // mListener.getFundTransferDetails().setMobileNumber(mobile);
                        // mListener.getFundTransferDetails().setSenderName(name);
                        // DMTUtilis.getDMTUtilsInstance().getBeneficiaryList(mobile,name,"338");
                    } else {
                        showDialog("Sorry!!", "Please check and enter the mobile number and Sender's name again");
                        senderMobileNumber.accessEditText().setText("");
                        senderName.accessEditText().setText("");
                    }
                }


        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.reg_beneficiary:
                mListener.goToReBeneficiaryRegistration(senderData.getSenderData());
                break;
            case R.id.check_commission:
                mListener.showCommissionDialog();
                break;
            case R.id.submit:
                int amount = 0;
                try {
                    String _amount = transferAmount.accessEditText().getText().toString();
                    amount = Integer.parseInt(_amount);
                } catch (Exception e) {

                }


                if (!Utils.isValidMobile(senderMobileNumber.accessEditText().getText().toString())) {
                    showDialog("Sorry!!", "Please check the sender's mobile number.");
                } else if (!Utils.isValidWord(senderName.accessEditText().getText().toString())) {
                    showDialog("Sorry!!", "Please enter the sender's Name properly.");
                } else if (senderRegID.accessEditText().getText().equals("")) {
                    showDialog("Sorry!!", "Please enter sender's Registration ID.");
                } else if (beneficiariesSpinner.getSelectedItem().equals(getString(R.string.default_beneficiary_spinner_prompt)) ||
                        beneficiariesSpinner.getSelectedItem().equals("Please select a Beneficiary.")) {
                    showDialog("Sorry!!", "Please select a Beneficiary.");
                } else if (amount < 100 || amount > 25000) {
                    showDialog("Sorry!!", "Please enter the amount between of 100/- and 25000/- to Transfer.");
                } else {
                    progressBar.show();
                    RetrofitClientInstance.getRetrofitInstance().create(DMTModuleService.class).fundTransfer(
                            senderData.getSenderData().getSenderMobileNo(),
                            senderData.getSenderData().getSenderName(),
                            senderData.getSenderData().getSenderId(),
                            senderData.getSelectedBeneficiary().getBeneficiaryId(),
                            amount,
                            UserData.getInstance().getId(),
                            BuildConfig.MOBILE_APPLICATION,
                            BuildConfig.MOBILE_VERSION_ID
                    ).enqueue(new Callback<FundTransferResponsePOJO>() {
                        @Override
                        public void onResponse(Call<FundTransferResponsePOJO> call, Response<FundTransferResponsePOJO> response) {
                            if (response.isSuccessful()) {
                                if (response.body().getStatus().equalsIgnoreCase("Success")) {
                                    senderData.getSelectedBeneficiary().setSelectedType(senderData.getTransferMode());
                                    mListener.goToConformation(response.body().getData(), senderData.getSelectedBeneficiary());
                                } else {
                                    showDialog("", response.body().getMsg());
                                }
                            } else {
                                showDialog("", response.message());
                            }
                            progressBar.cancel();
                        }

                        @Override
                        public void onFailure(Call<FundTransferResponsePOJO> call, Throwable t) {
                            showDialog("", t.getMessage());
                            progressBar.cancel();
                        }
                    });
                    break;
                }
        }
    }
}
