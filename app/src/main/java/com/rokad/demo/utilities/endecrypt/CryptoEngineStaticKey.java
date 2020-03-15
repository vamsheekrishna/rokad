package com.rokad.demo.utilities.endecrypt;

import android.annotation.SuppressLint;
import android.util.Base64;

import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;


@SuppressLint("TrulyRandom")

public class CryptoEngineStaticKey {

    private static final String AES = "AES";
    private static Cipher sCipher;
    private static SecretKeySpec sSecretKeySpec;
    private static byte[] sEncrypted;
    private static byte[] sDecrypted;
    private static byte[] sClear;

    static {

        try {
            sCipher = Cipher.getInstance("AES/ECB/PKCS7Padding");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        }
    }

    /**
     * The method is for Encryption of data
     *
     * @param data to be used for Encryption
     * @return Encrypted data
     * @throws Exception
     */
    public static String encrypt(String data, String key) throws Exception {

        sClear = data.getBytes();
        sSecretKeySpec = new SecretKeySpec(key.getBytes(), AES);
        sCipher.init(Cipher.ENCRYPT_MODE, sSecretKeySpec);
        sEncrypted = sCipher.doFinal(sClear);

        String sResultString = Base64.encodeToString(sEncrypted, Base64.DEFAULT);
        clearTempData();
        return sResultString;
    }

    /**
     * The method is for Decryption of data
     *
     * @param encryptedData to be used for Decryption
     * @return Decrypted data
     * @throws Exception
     */
    public static String decrypt(String encryptedData, String key) throws Exception {

        sSecretKeySpec = new SecretKeySpec(key.getBytes(), AES);
        sCipher.init(Cipher.DECRYPT_MODE, sSecretKeySpec);
        sDecrypted = sCipher.doFinal(Base64.decode(encryptedData, Base64.DEFAULT));
        String sResultString = new String(sDecrypted);
        clearTempData();
        return sResultString;
    }

    private static void clearTempData() {
        sClear = null;
        sSecretKeySpec = null;
        sEncrypted = null;
        sDecrypted = null;
    }

    public static String decryptWithIV(String encrypted, String _key)
            throws GeneralSecurityException {
        byte[] key = _key.getBytes();
        if (key.length != 16) {
            throw new IllegalArgumentException("Invalid key size.");
        }

        byte[] ciphertextBytes = Base64.decode(encrypted.getBytes(), Base64.DEFAULT);
        IvParameterSpec iv = new IvParameterSpec(ciphertextBytes, 0, 16);
        ciphertextBytes = Arrays.copyOfRange(ciphertextBytes, 16,
                ciphertextBytes.length);

        SecretKeySpec skeySpec = new SecretKeySpec(key, "AES");

        Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
        cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
        byte[] original = cipher.doFinal(ciphertextBytes);

        // remove zero bytes at the end
        int lastLength = original.length;
        for (int i = original.length - 1; i > original.length - 16; i--) {
            if (original[i] == (byte) 0) {
                lastLength--;
            } else {
                break;
            }
        }

        return new String(original, 0, lastLength);
    }

}