package io.itteam.gasmyas;


import io.itteam.gasmyas.rest.GetActivated;
import io.itteam.gasmyas.rest.GetDeactivated;
import io.itteam.gasmyas.rest.GetUser;
import io.itteam.gasmyas.rest.PostRegistration;
import io.itteam.gasmyas.rest.SignIn;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroClient {

    private static final String ROOT_URL = "http://192.168.9.220:44444";

    private static Retrofit getRetrofitInstance() {
        return new Retrofit.Builder()
                .baseUrl(ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static PostRegistration getCode() {
        return getRetrofitInstance().create(PostRegistration.class);
    }

    public static SignIn getApiServiceSignIn() {
        return getRetrofitInstance().create(SignIn.class);
    }

    public static GetActivated getApiServiceGetActivated() {
        return getRetrofitInstance().create(GetActivated.class);
    }

    public static GetDeactivated getApiServiceGetDeactivated() {
        return getRetrofitInstance().create(GetDeactivated.class);
    }
    public static GetUser getUser() {
        return getRetrofitInstance().create(GetUser.class);
    }

}