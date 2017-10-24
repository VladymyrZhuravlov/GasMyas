package io.itteam.gasmyas.rest;

import io.itteam.gasmyas.json.AccessToken;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface SignIn {

    @Headers({"Content-Type: application/json"})
    @GET("/phone")
    Call<AccessToken> getTask(@Query("phone") String phone, @Query("code") String code);

}
