package com.example.inaki.parkingapp;

import com.example.inaki.parkingapp.Data.ParkingData;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface API {

    String BASE_URL = "http://ridecellparking.herokuapp.com/";

    @GET("api/v1/parkinglocations")
    Call<List<ParkingData>> getSlots();
}
