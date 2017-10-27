package io.itteam.gasmyas;


import java.util.concurrent.TimeUnit;

import io.itteam.gasmyas.rest.GetActivated;
import io.itteam.gasmyas.rest.GetDeactivated;
import io.itteam.gasmyas.rest.GetResalt;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroClientResalt {

    private static final String ROOT_URL = "http://83.218.236.146:44444";

    private static Retrofit getRetrofitInstance() {
        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .build();
        return new Retrofit.Builder()
                .baseUrl(ROOT_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static GetActivated getApiServiceGetActivated() {
        return getRetrofitInstance().create(GetActivated.class);
    }

    public static GetDeactivated getApiServiceGetDeactivated() {
        return getRetrofitInstance().create(GetDeactivated.class);}
    public static GetResalt getGetresalt() {
        return getRetrofitInstance().create(GetResalt.class);
    }

}