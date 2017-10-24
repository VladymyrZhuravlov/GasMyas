package io.itteam.gasmyas.rest;

import io.itteam.travel.json.users.Response;
import io.itteam.travel.json.users.ResponseUser;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface PostRegistration {
    @Headers({"Content-Type: application/json"})
    @POST("/signup")
    Call<Response> registerUser(@Body ResponseUser registrationBody);
}
