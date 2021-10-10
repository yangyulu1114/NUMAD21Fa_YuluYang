package edu.neu.madcourse.NUMAD21Fa_YuluYang;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class LocatorActivity extends AppCompatActivity implements LocationListener {

    private static final int REQUEST_PERMISSION = 1;

    private static final String[] PERMISSIONS = {
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.INTERNET,
    };

    private LocationManager mLocationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locator);

        if (requestPermission()) {
            requestLocation();
        }
    }

    private boolean requestPermission() {
        for (String permission : PERMISSIONS) {
            if (ActivityCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, PERMISSIONS, REQUEST_PERMISSION);
                return false;
            }
        }
        return true;
    }

    @SuppressLint("MissingPermission")
    private void requestLocation() {
        mLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        List<String> providers = mLocationManager.getProviders(true);
        String locationProvider;
        if (providers.contains(LocationManager.NETWORK_PROVIDER)) {
            locationProvider = LocationManager.NETWORK_PROVIDER;
        } else if (providers.contains(LocationManager.GPS_PROVIDER)) {
            locationProvider = LocationManager.GPS_PROVIDER;
        } else {
            Toast.makeText(this, "No location provider, please turn on network or GPS", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        Location location = mLocationManager.getLastKnownLocation(locationProvider);
        if (location != null) {
            showLocation(location);
        }
        mLocationManager.requestLocationUpdates(locationProvider, 0, 0, this);
    }

    private void showLocation(Location location) {
        TextView locator = findViewById(R.id.locator);
        locator.setText("Longitude : " + location.getLongitude() + "\n"
                + "Latitude  : " + location.getLatitude());
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        showLocation(location);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_PERMISSION:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    requestLocation();
                } else {
                    finish();
                }
                break;
            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        if (mLocationManager != null) {
            mLocationManager.removeUpdates(this);
        }
        super.onDestroy();
    }
}