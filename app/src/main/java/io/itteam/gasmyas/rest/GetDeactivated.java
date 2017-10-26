package io.itteam.gasmyas.rest;


import io.itteam.gasmyas.json.activated.Activator;
import io.itteam.gasmyas.json.user.User;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface GetDeactivated {

    @Headers({"Content-Type: application/json"})
    @GET("/api/v1/count/deactivated")
    Call<Activator> deactivated(@Header("Authorization") String v, @Query("count") String phone);
}
