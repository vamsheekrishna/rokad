package com.rokad.encrypt;


abstract class EncryptionKey {
    protected String key;
    abstract void getKey(String key);
}