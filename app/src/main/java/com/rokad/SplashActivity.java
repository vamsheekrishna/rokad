package com.rokad;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.rokad.encrypt.Encryption;
import com.rokad.encrypt.encryptionFactory;
import com.rokad.encrypt.EncryptionHelper;
import com.rokad.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textView = findViewById(R.id.text_id);
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
        }
    }
}
