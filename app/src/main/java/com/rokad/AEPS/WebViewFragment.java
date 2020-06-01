package com.rokad.AEPS;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.ValueCallback;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.rokad.R;
import com.rokad.dmt.pojos.TransactionProcessPOJO;
import com.rokad.utilities.views.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link WebViewFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WebViewFragment extends BaseFragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private WebSettings webSettings;
    // TODO: Rename and change types of parameters
    private TransactionProcessPOJO.TransactionProcessData mParam1;
    private String mParam2;
    private WebView webView;
    private String data = "Hi i am from Android string!!!";

    public WebViewFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment WebViewFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static WebViewFragment newInstance(TransactionProcessPOJO.TransactionProcessData param1, String param2) {
        WebViewFragment fragment = new WebViewFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = (TransactionProcessPOJO.TransactionProcessData) getArguments().getSerializable(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_web_view, container, false);
    }

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        webView = view.findViewById(R.id.webView);
        view.findViewById(R.id.button).setOnClickListener(this);
        view.findViewById(R.id.returnValue).setOnClickListener(this);
        // Enable Javascript
        webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);

        CustomWebViewClient webViewClient = new CustomWebViewClient();
        webView.setWebViewClient(webViewClient);
        // Add the custom WebViewClient class
        // Add the javascript interface
        webView.addJavascriptInterface(new JavaScriptInterface(), "interface");
        // Load the example html file to the WebView
        // webView.loadUrl("file:///android_asset/index.html");
        // webView.loadUrl("https://rokad.in/home");

        // webView.loadUrl(buffer.toString());
        String buffer = "?request_id=" + mParam1.getRequest_id() +
                "&sourceid=" + mParam1.getSourceid() +
                "&mastercode=" + mParam1.getMastercode() +
                "&agentcode=" + mParam1.getAgentcode() +
                "&trackid=" + mParam1.getTrackid() +
                "&checksum=" + mParam1.getChecksum();
        webView.postUrl(mParam1.getRETURNURL(), buffer.getBytes());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button:
                webView.loadUrl("javascript:callFromApp('"+data+"');");
                break;
            case R.id.returnValue:
                webView.evaluateJavascript("javascript:callFromAppWithReturn();", new ValueCallback<String>() {
                    @Override
                    public void onReceiveValue(String s) {
                        Toast.makeText(requireActivity(), s, Toast.LENGTH_LONG).show();
                    }
                });
                break;
        }
    }

    /**
     * CustomWebViewClient is used to add a custom hook to the url loading.
     */
    private class CustomWebViewClient extends WebViewClient {

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            return super.shouldOverrideUrlLoading(view, url);
        }
        /*@Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {

            // If the url to be loaded starts with the custom protocol, skip
            // loading and do something else
            if (url.startsWith("tanelikorri://")) {

                Toast.makeText(requireActivity(), "Custom protocol call", Toast.LENGTH_LONG).show();

                return true;
            }

            return false;
        }*/
    }

    /**
     * JavaScriptInterface is the interface class for the application code calls. All public methods
     * annotated with {@link android.webkit.JavascriptInterface JavascriptInterface } in this class
     * can be called from JavaScript.
     */
    private class JavaScriptInterface {

        @JavascriptInterface
        public void callFromJS() {
            Toast.makeText(requireActivity(), "JavaScript interface call", Toast.LENGTH_LONG).show();
        }
    }
}
