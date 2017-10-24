package io.itteam.gasmyas.rest;

import io.itteam.gasmyas.json.PhoneNumber;
import io.itteam.gasmyas.json.PostCode;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface PostRegistration {
    @Headers({"Content-Type: application/json"})
    @POST("/signin")
    Call<PostCode> getCode(@Body PhoneNumber registrationBody);
}
