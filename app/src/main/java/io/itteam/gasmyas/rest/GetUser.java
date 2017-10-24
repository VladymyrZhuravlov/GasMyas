package io.itteam.gasmyas.rest;

import io.itteam.gasmyas.json.user.User;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;

public interface GetUser {

    @Headers({"Content-Type: application/json"})
    @GET("/api/v1/info")
    Call<User> getTask(@Header("Authorization") String v);

}
