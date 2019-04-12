package com.checkmyuniverse.locationfetchersample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.checkmyuniverse.locationfetchhelper.LocationFetchHelper;
import com.checkmyuniverse.locationfetchhelper.LocationPermissionListener;
import com.google.android.gms.location.LocationRequest;

public class MainActivity extends AppCompatActivity {
    private static final long LOCATION_FASTEST_INTERVAL = 5 * 1000;
    private static final long LOCATION_INTERVAL = 20 * 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getLocationPermission();
    }

    private void getLocationPermission() {
        new LocationFetchHelper(this, createLocationRequest(), new LocationPermissionListener() {
            @Override
            public void onPermissionGranted() {
                Toast.makeText(MainActivity.this, "granted", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPermissionDenied(String errorMessage) {
                Toast.makeText(MainActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
            }
        });
    }

    protected LocationRequest createLocationRequest() {
        LocationRequest locationRequest = new LocationRequest();
        locationRequest.setInterval(LOCATION_INTERVAL);
        locationRequest.setFastestInterval(LOCATION_FASTEST_INTERVAL);
        locationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        return locationRequest;
    }
}
