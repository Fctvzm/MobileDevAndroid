package com.example.assem.map;

import android.location.Address;
import android.location.Geocoder;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private List<Address> addresses;
    private EditText text;
    private double latitude;
    private double longitude;
    private Address address;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        addresses = new ArrayList<>();
        text = (EditText)findViewById(R.id.text);

        mapFragment.getMapAsync(this);
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
        UiSettings uiSettings = mMap.getUiSettings();
        uiSettings.setZoomControlsEnabled(true);
    }

    public void onClick(View view) {
        Geocoder geocoder = new Geocoder(this);
        try {addresses = geocoder.getFromLocationName(text.getText().toString(), 1);}
        catch (IOException e) {e.printStackTrace();}
        if(addresses.size() > 0) {
            address = addresses.get(0);
            latitude = address.getLatitude();
            longitude = address.getLongitude();
            LatLng search = new LatLng(latitude, longitude);
            mMap.addMarker(new MarkerOptions().position(search).title(address.getCountryName() + ", " + address.getLocality()));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(search));
            mMap.animateCamera(CameraUpdateFactory.zoomTo(14), 2000, null);
        } else {
            Toast.makeText(this, "No such address", Toast.LENGTH_LONG).show();
        }
    }

}
