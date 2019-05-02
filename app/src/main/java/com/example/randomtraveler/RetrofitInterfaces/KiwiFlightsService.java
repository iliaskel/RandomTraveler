package com.example.randomtraveler.RetrofitInterfaces;


import com.example.randomtraveler.KiwiFlightsModel.Feed;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;


public interface KiwiFlightsService {

    @GET("/flights")
    Call<Feed> getFlightResults(@QueryMap Map<String,String> params);

}
