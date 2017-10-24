package io.itteam.gasmyas.rest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface PostRegistration {
    @Headers({"Content-Type: application/json"})
    @POST("/signup")
    Call<Response> registerUser(@Body ResponseUser registrationBody);
}
