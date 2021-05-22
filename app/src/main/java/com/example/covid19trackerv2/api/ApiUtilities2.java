package com.example.covid19trackerv2.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiUtilities2 {
    private static Retrofit retrofit = null;

    public static  ApiInterface2 getApiInterface2(){
        if(retrofit==null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(ApiInterface2.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(ApiInterface2.class);
    }
}
