package io.itteam.gasmyas;


public class RetroClient {

    private static final String ROOT_URL = "http://192.168.9.220:44444";

    private static Retrofit getRetrofitInstance() {
        return new Retrofit.Builder()
                .baseUrl(ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static GetRoute getRoute() {
        return getRetrofitInstance().create(GetRoute.class);
    }

}