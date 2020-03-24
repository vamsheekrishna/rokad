package com.rokad.encrypt;



public class encryptionFactory {
	
	 public static Encryption getEncryptionByName(String encryptionSTR) throws Exception{
         
	        if("AES".equalsIgnoreCase(encryptionSTR)){
	            return new AESEncryption();
	        } else if("DES".equalsIgnoreCase(encryptionSTR)){
	            return new DESEncryption();
	        }
	        throw new Exception("Invalid Encryption...");
	    }
	

}
