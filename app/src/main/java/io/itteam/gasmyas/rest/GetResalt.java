package io.itteam.gasmyas.rest;

import io.itteam.gasmyas.json.JsonResalt;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;

public interface GetResalt {

    @Headers({"Content-Type: application/json"})
    @GET("/api/v1/count/command")
    Call<JsonResalt> getTask(@Header("Authorization") String v);

}
