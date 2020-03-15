package com.rokad.demo.utilities;

public class EncryptAndDecrypt {


    public static boolean isKeyLengthValid(String key) {
        return key.length() == 16 || key.length() == 24 || key.length() == 32;
    }
    /*

    Authentication : Basic cG93ZXJ1c2VyOnRyaW1heCMxMjM
    x-api-key: abcdefghijklmn
    Content-Type: application/x-www-form-urlencoded
    mobile_no: 9960703227
    password: Trimax@123
    function rokad_decrypt($encryptedText,$key)
    {
        // decode encrypted Text
        $decoded = base64_decode($encryptedText);

        // Hashing Api key
        $hash = hash( "sha256", $key , true );
        // Convert into 16 chr string
        $string = substr($hash, 0,16);

        $plainText = mcrypt_decrypt(MCRYPT_RIJNDAEL_128, $string, $decoded, MCRYPT_MODE_CBC, $string);


        $len = strlen($plainText);
        $pad = ord($plainText[$len-1]);

        return substr($plainText, 0, $len - $pad);
    }

MCRYPT_RIJNDAEL_128 = rijndael-128

$key = $this->pos_encryption_key = 'QWRTEfnfdys635';
     */
}
