package com.rokad.encrypt;

public interface Encryption {

    
	public String encrypt(String encryptionSTR);
    public String decrypt(String decryptionSTR);
    
    public void setKey(String key);
    

}
