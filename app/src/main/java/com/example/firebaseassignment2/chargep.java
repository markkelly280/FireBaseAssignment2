package com.example.firebaseassignment2;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.firebaseassignment2.databinding.ActivityChargepBinding;

public class chargep extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityChargepBinding binding;
    final private int REQUEST_COARSE_ACCESS = 123;
    boolean permissionGranted = false;
    LocationManager lm;
    LocationListener locationListener;
    private int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityChargepBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    private class MyLocationListener implements LocationListener {

        public void getHardCodedLocations() {
            LatLng a = new LatLng(54.9483, -7.7390);
            LatLng b = new LatLng(54.9121, -7.1537);
            LatLng c = new LatLng(54.8172, -7.4613);
            LatLng d = new LatLng(54.5839, -7.3017);
            LatLng e = new LatLng(54.6332, -6.7512);
            LatLng f = new LatLng(54.7491, -6.5946);
            LatLng g = new LatLng(54.9799, -6.7045);
            LatLng h = new LatLng(54.8547, -6.2840);
            LatLng i = new LatLng(54.4884, -6.7608);
            LatLng j = new LatLng(54.4086, -6.4284);
            LatLng k = new LatLng(54.6092, -5.9364);
            LatLng l = new LatLng(54.5073, -5.9034);
            LatLng m = new LatLng(54.8577, -6.2873);
            LatLng n = new LatLng(55.2341, -6.3792);
            LatLng o = new LatLng(55.1872, -6.6472);

            mMap.addMarker(new MarkerOptions().position(a).title("Electric Charging Point"));
            mMap.addMarker(new MarkerOptions().position(b).title("Electric Charging Point"));
            mMap.addMarker(new MarkerOptions().position(c).title("Electric Charging Point"));
            mMap.addMarker(new MarkerOptions().position(d).title("Electric Charging Point"));
            mMap.addMarker(new MarkerOptions().position(e).title("Electric Charging Point"));
            mMap.addMarker(new MarkerOptions().position(f).title("Electric Charging Point"));
            mMap.addMarker(new MarkerOptions().position(g).title("Electric Charging Point"));
            mMap.addMarker(new MarkerOptions().position(h).title("Electric Charging Point"));
            mMap.addMarker(new MarkerOptions().position(i).title("Electric Charging Point"));
            mMap.addMarker(new MarkerOptions().position(j).title("Electric Charging Point"));
            mMap.addMarker(new MarkerOptions().position(k).title("Electric Charging Point"));
            mMap.addMarker(new MarkerOptions().position(l).title("Electric Charging Point"));
            mMap.addMarker(new MarkerOptions().position(m).title("Electric Charging Point"));
            mMap.addMarker(new MarkerOptions().position(n).title("Electric Charging Point"));
            mMap.addMarker(new MarkerOptions().position(o).title("Electric Charging Point"));
        }


        @Override
        public void onLocationChanged(@NonNull Location location) {
            if (counter == 0) {
                Toast.makeText(getBaseContext(), "Current Location: Lat: " + location.getLatitude()
                        + " Lng: " + location.getLongitude(), Toast.LENGTH_SHORT).show();
                LatLng p = new LatLng(location.getLatitude(), location.getLongitude());
                mMap.clear();
                getHardCodedLocations();
                mMap.addMarker(new MarkerOptions().position(p).title("Current Location"));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(p, 8.0f));
                counter = 1;
            }
        }

        @Override
        public void onProviderEnabled(@NonNull String provider) {
            LocationListener.super.onProviderEnabled(provider);
        }

        @Override
        public void onProviderDisabled(@NonNull String provider) {
            LocationListener.super.onProviderDisabled(provider);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locationListener = new MyLocationListener();


        if (ActivityCompat.checkSelfPermission(this, ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{ACCESS_COARSE_LOCATION,
                    ACCESS_FINE_LOCATION}, REQUEST_COARSE_ACCESS);
            return;
        } else {
            permissionGranted = true;
        }
        if (permissionGranted) {
            lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_COARSE_ACCESS:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    permissionGranted = true;
                    if (ActivityCompat.checkSelfPermission(this, ACCESS_FINE_LOCATION)
                            != PackageManager.PERMISSION_GRANTED
                            && ActivityCompat.checkSelfPermission(this, ACCESS_COARSE_LOCATION)
                            != PackageManager.PERMISSION_GRANTED) {
                        return;
                    }
                    lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
                } else {
                    permissionGranted = false;
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        if (ActivityCompat.checkSelfPermission(this, ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{ACCESS_COARSE_LOCATION,
                    ACCESS_FINE_LOCATION}, REQUEST_COARSE_ACCESS);
            return;
        } else {
            permissionGranted = true;
        }
        if (permissionGranted) {
            lm.removeUpdates(locationListener);
        }
    }
}
