package com.rokad.demo.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.rokad.demo.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Remove notification bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        try {
            // String encrypt = CryptoEngineStaticKey.encrypt("Sample text","QWRTEfnfdys635");
              // String encrypt = CryptoEngineStaticKey.decrypt("Sample text","QWRTEfnfdys635");

            //String text = CryptoEngineStaticKey.decryptWithIV("ZDGmy9DQ3Y8zBPsrq8n1/w==","abcdefghijklmn");
            // Log.d("text", " decrypt: ");
//            final String ssid = "MY_WIFI_SSID";
//            final String encWifiKey = "myEncryptedWifiKeyString";
//
//            String wifiKey = "";
//            try {
//                wifiKey = new String(SimpleCrypto.decrypt(SimpleCrypto.getHash(ssid), Base64.decode(encWifiKey, Base64.DEFAULT)));
//                int size = wifiKey.length();
//            } catch (InvalidKeyException e) {
//                e.printStackTrace();
//            } catch (IllegalBlockSizeException e) {
//                e.printStackTrace();
//            } catch (BadPaddingException e) {
//                e.printStackTrace();
//            } catch (NoSuchAlgorithmException e) {
//                e.printStackTrace();
//            } catch (NoSuchPaddingException e) {
//                e.printStackTrace();
//            }

        } catch (Exception e) {
            e.printStackTrace();
            Log.d("", "");
        }

        int SPLASH_TIME_OUT = 3000;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, Authentication.class);
                startActivity(intent);
//                if(Utilities.isNewUser(SplashActivity.this)) {
//                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
//                    startActivity(intent);
//                } else {

//                }
                finish();
            }
        }, SPLASH_TIME_OUT);
    }

}
