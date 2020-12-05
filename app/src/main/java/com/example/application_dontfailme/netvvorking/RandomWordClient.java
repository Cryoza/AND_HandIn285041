package com.example.application_dontfailme.netvvorking;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RandomWordClient {

    private RandomWordAPI randomWordAPI;

    public RandomWordClient(String domain) {
        Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
                .baseUrl("https://random-word-api.herokuapp.com")
                .addConverterFactory(GsonConverterFactory.create());

        this.randomWordAPI = retrofitBuilder.build().create(RandomWordAPI.class);
    }

    public RandomWordAPI getRandomWordAPI() {
        return randomWordAPI;
    }
}
