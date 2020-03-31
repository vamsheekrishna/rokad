package com.rokad.rokad_api.endpoints;

public interface URL {

    // https://rokad.in/rest_server/mplan/getDataPlans

    String URL_BASE = "rest_server/";
    String LOGIN = URL_BASE+"rokad/login";
    String FORGOT_PASSWORD = URL_BASE+"rokad/forgot_password";
    String RECHARGE = URL_BASE+"Tsoapi/recharge/";
    String GET_PLANS = URL_BASE+ "mplan/getDataPlans";
}
