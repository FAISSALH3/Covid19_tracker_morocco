package com.example.covid19trackerv2.api;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface2 {
    static final String BASE_URL = "https://api.caw.sh/v3/covid-19/vaccine/coverage/";

    @GET("countries")
    Call<List<CountryData>> getCountryData();
}
