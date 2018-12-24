package com.example.inaki.parkingapp;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.inaki.parkingapp.Adapter.ParkAdapter;
import com.example.inaki.parkingapp.Data.ParkingData;
import com.example.inaki.parkingapp.ParkingViewModel.ParkingViewModel;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private static final String TAG = MapsActivity.class.getSimpleName();
    private GoogleMap mMap;

    ParkAdapter mAdapter;
    RecyclerView mRecycler;
    String latitud,longitud,cost;
    double dLat,dLng,price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        Log.d(TAG, "onCreate: inflate");
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        Log.d(TAG, "onCreate: assign ready callback");

        ParkingViewModel model = ViewModelProviders.of(this).get(ParkingViewModel.class);

        model.getSlots().observe(this, new Observer<List<ParkingData>>() {
            @Override
            public void onChanged(@Nullable List<ParkingData> parkList) {
                mAdapter = new ParkAdapter(MapsActivity.this, parkList);
                mRecycler.setAdapter(mAdapter);
            }
        });
        Log.d(TAG, "onCreate: ViewModel ready");


        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if(bundle != null){
            latitud = (String) bundle.get("latitude");
            longitud = (String) bundle.get("longitude");
            cost = (String) bundle.get("cost");

            dLat = Double.parseDouble(latitud);
            dLng = Double.parseDouble(longitud);
            price = Double.parseDouble(cost);

            Log.d(TAG,"GETTING THE DATA");
        }
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(dLat,dLng);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
}
