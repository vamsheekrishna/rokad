package com.rokad.rokad_api;

import android.util.Base64;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.rokad.BuildConfig;

import java.io.IOException;
import java.security.cert.CertificateException;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClientInstance {
    private static Retrofit retrofit;
    private static final String BASE_URL = "https://testingrokad.msrtcors.com";

    private static String creds = String.format("%s:%s", "android@roadpulse.in", "api@123");
    private static String auth = "Basic " + Base64.encodeToString(creds.getBytes(), Base64.NO_WRAP);
    private static String apiKey = BuildConfig.DEBUG?"abcdefghijklmn":"abcdefghijklmn";

    // /rest_server/rokad/login
    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            try {

                // Install the all-trusting trust manager
                final SSLContext sslContext = SSLContext.getInstance("SSL");
                sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
                // Create an ssl socket factory with our all-trusting manager
                final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();
                HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
                logging.setLevel(HttpLoggingInterceptor.Level.BODY);

                Interceptor basicAuth = new Interceptor() {
                    @Override
                    public Response intercept(Interceptor.Chain chain) throws IOException {
                        Request request = chain.request()
                                .newBuilder()
                                .addHeader("Content-Type","application/x-www-form-urlencoded")
                                .addHeader("X-API-KEY",apiKey)
                                .addHeader("Authorization", auth.replace("\n",""))
                                .build();
                        return chain.proceed(request);
                    }
                };

                OkHttpClient client = new OkHttpClient.Builder()
                        .sslSocketFactory(sslSocketFactory, (X509TrustManager)trustAllCerts[0])
                        .addInterceptor(basicAuth)
                        .addInterceptor(logging)
                        .connectTimeout(60, TimeUnit.SECONDS)
                        .readTimeout(60, TimeUnit.SECONDS)
                        .build();

                Gson gs = new GsonBuilder().setLenient().create();

                retrofit = new retrofit2.Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .client(client)
                        .addConverterFactory(GsonConverterFactory.create(gs))
                        .build();
            } catch (Exception e) {
                Log.d("Exception", "Exception : "+e.getMessage());
            }

        }
        return retrofit;
    }

    private final static TrustManager[] trustAllCerts = new TrustManager[]{
            new X509TrustManager() {
                @Override
                public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                }

                @Override
                public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                }

                @Override
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return new java.security.cert.X509Certificate[]{};
                }
            }
    };
}
