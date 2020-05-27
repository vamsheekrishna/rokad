package com.rokad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.rokad.authentication.LoginActivity;
import com.rokad.model.UserData;
import com.rokad.home.HomeActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                if(UserData.isSessionExpire() && null == UserData.getInstance().getFirstName()) {
                    startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                } else {
                    startActivity(new Intent(SplashActivity.this, HomeActivity.class));
                }
                finish();
            }
        },1000);

        /*TextView textView = findViewById(R.id.text_id);
        Encryption AESencryption;
        EncryptionHelper objEncryptionHelper = new EncryptionHelper();
        try {
            AESencryption = encryptionFactory.getEncryptionByName("AES");
            String input = "Rajan";
            String key = "QWRTEfnfdys635";//E-m!tr@2016
            AESencryption.setKey(key);
            String output = AESencryption.encrypt(input);
            System.out.println("des string : " + AESencryption.encrypt(input));
            System.out.println("des string : " + AESencryption.decrypt(output));
            key = "Htzh1otlX4LDEq0lweG4FQ==";
            System.out.println( key +" --> " + AESencryption.decrypt("Htzh1otlX4LDEq0lweG4FQ=="));
            textView.setText( AESencryption.decrypt("Htzh1otlX4LDEq0lweG4FQ==") +" --> " + AESencryption.encrypt(AESencryption.decrypt("Htzh1otlX4LDEq0lweG4FQ==")));
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }
}
