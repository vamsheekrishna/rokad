package com.rokad.utilities.encrypt;


abstract class EncryptionKey {
    protected String key;
    abstract void getKey(String key);
}