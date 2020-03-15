package com.rokad.demo.utilities.endecrypt;

import android.annotation.SuppressLint;
import android.util.Base64;
import android.util.Log;

import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

@SuppressLint("TrulyRandom")
public class CryptoEngine {


    public static String CipherValue;
	private static final String AES = "AES";
	private static Cipher sCipher;
	private static SecretKeySpec sSecretKeySpec;
	private static byte[] sEncrypted;
	private static byte[] sDecrypted;
	private static byte[] sClear;

	static {
// http://www.logikdev.com/2010/11/01/encrypt-with-php-decrypt-with-java/
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
	 * @param data
	 *            to be used for Encryption
	 * @return Encrypted data
	 * @throws Exception
	 */
	public static String encrypt(String data) throws Exception {
		String sResultString = null;
		if(null != data && data.length()>0) {
			try{
				sClear = data.getBytes();
				sSecretKeySpec = new SecretKeySpec(CipherValue.getBytes(), AES);
				sCipher.init(Cipher.ENCRYPT_MODE, sSecretKeySpec);
				sEncrypted = sCipher.doFinal(sClear);

				sResultString = Base64
						.encodeToString(sEncrypted, Base64.DEFAULT);
				clearTempData();
			} catch (Exception e){
				Log.d("","");
			}
		}
		return sResultString;
	}

	/**
	 * The method is for Decryption of data
	 * 
	 * @param encryptedData
	 *            to be used for Decryption
	 * @return Decrypted data
	 * @throws Exception
	 */
	public static String decrypt(String encryptedData) throws Exception {
		String sResultString = null;
		try {
			if(null != encryptedData && encryptedData.length()>0) {
				sSecretKeySpec = new SecretKeySpec(CipherValue.getBytes(), AES);
				sCipher.init(Cipher.DECRYPT_MODE, sSecretKeySpec);
				sDecrypted = sCipher.doFinal(Base64.decode(encryptedData,
						Base64.DEFAULT));
				sResultString = new String(sDecrypted);
				clearTempData();
			}
		} catch (Exception e){
			sResultString = encryptedData;
		}

		return sResultString;
	}
	public static int decrypt(int encryptedValue) throws Exception {
		String encryptedData = Integer.toString(encryptedValue);
		sSecretKeySpec = new SecretKeySpec(CipherValue.getBytes(), AES);
		sCipher.init(Cipher.DECRYPT_MODE, sSecretKeySpec);
		sDecrypted = sCipher.doFinal(Base64.decode(encryptedData,
				Base64.DEFAULT));
		String sResultString = new String(sDecrypted);
		clearTempData();
		return Integer.parseInt(sResultString);
	}
	private static void clearTempData() {
		sClear = null;
		sSecretKeySpec = null;
		sEncrypted = null;
		sDecrypted = null;
	}

}