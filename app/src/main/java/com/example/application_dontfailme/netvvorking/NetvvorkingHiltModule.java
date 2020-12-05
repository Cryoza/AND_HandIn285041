package com.example.application_dontfailme.netvvorking;


import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ActivityComponent;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
@InstallIn(ActivityComponent.class)
public class NetvvorkingHiltModule {

    @Provides
    public static RandomWordAPI bindRandomWordApi() {
        Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
                .baseUrl("https://random-word-api.herokuapp.com")
                .addConverterFactory(GsonConverterFactory.create());

        return retrofitBuilder.build().create(RandomWordAPI.class);
    }
}
