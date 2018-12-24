package com.example.inaki.parkingapp.ParkingViewModel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.inaki.parkingapp.API;
import com.example.inaki.parkingapp.Data.ParkingData;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ParkingViewModel extends ViewModel {

    private MutableLiveData<List<ParkingData>> parkingList;

    public LiveData<List<ParkingData>> getSlots(){

        if(parkingList == null){
            parkingList = new MutableLiveData<List<ParkingData>>();
            loadParkingSlots();
        }

        return parkingList;
    }

    private void loadParkingSlots(){

        Retrofit retrofit = new Retrofit.Builder().baseUrl(API.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build();

        API api = retrofit.create(API.class);
        Call<List<ParkingData>> call = api.getSlots();

        call.enqueue(new Callback<List<ParkingData>>() {
            @Override
            public void onResponse(Call<List<ParkingData>> call, Response<List<ParkingData>> response) {
                parkingList.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<ParkingData>> call, Throwable t) {

            }
        });

    }

}

