package com.rokad.utilities;

public class Utils {

    public static boolean isValidMobile(String phone) {
        if ((phone != null &&  !phone.isEmpty()) && phone.length() == 10
                &&
                (Integer.parseInt(String.valueOf(phone.charAt(0))) > 5 && Integer.parseInt(String.valueOf(phone.charAt(0))) < 10))
        {
            return android.util.Patterns.PHONE.matcher(phone).matches();
        }
        return false;
    }
}
