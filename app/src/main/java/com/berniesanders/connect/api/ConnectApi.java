package com.berniesanders.connect.api;

import com.berniesanders.connect.gson.JsonApiResponse;

import retrofit2.http.GET;
import rx.Observable;

public interface ConnectApi {
    String BASE_URL = "https://connect.berniesanders.com/api/";

    @GET("action_alerts")
    Observable<JsonApiResponse> getActionAlerts();
}
