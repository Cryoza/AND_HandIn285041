package com.example.application_dontfailme.netvvorking;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RandomWordAPI {

    @GET("word?swear=0")
    Call<String[]> getWord(@Query("number") int n);
}
