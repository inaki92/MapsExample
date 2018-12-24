package com.example.inaki.parkingapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;


import com.example.inaki.parkingapp.Data.ParkingData;
import com.example.inaki.parkingapp.MapsActivity;

import java.util.List;

public class ParkAdapter extends RecyclerView.Adapter<ParkAdapter.ParkingViewHolder> {

    Context mCtx;
    List<ParkingData> parkList;

    public ParkAdapter(Context mCtx, List<ParkingData> parkList){
        this.mCtx = mCtx;
        this.parkList = parkList;
    }


    class ParkingViewHolder extends RecyclerView.ViewHolder {

        String lat,lng,cost;

        public ParkingViewHolder(@NonNull View itemView) {
            super(itemView);

            //Sending the data to the MapsAcitivity
            Intent intent =new Intent(mCtx,MapsActivity.class);
            intent.putExtra("latitude",lat);
            intent.putExtra("longitude",lng);
            intent.putExtra("cost",cost);
            mCtx.startActivity(intent);
        }
    }

    @NonNull
    @Override
    public ParkAdapter.ParkingViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ParkAdapter.ParkingViewHolder holder, int i) {
        //final ParkingData park = parkList.get(i);

        holder.lat = parkList.get(i).getLat();
        holder.lng = parkList.get(i).getLng();
        holder.cost = parkList.get(i).getCostPerMinute();
    }

    @Override
    public int getItemCount() {
        return parkList.size();
    }
}

