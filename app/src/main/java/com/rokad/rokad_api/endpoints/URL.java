package com.rokad.rokad_api.endpoints;

public interface URL {

    String URL_LOGIN_BASE = "rest_server/rokad/";
    String LOGIN = URL_LOGIN_BASE+"login";
    String FORGOT_PASSWORD = URL_LOGIN_BASE+"forgot_password";
    String URL_MOBILE_BASE = "rest_server/Tsoapi/";
    String RECHARGE = URL_MOBILE_BASE+"recharge/";
}
