package com.roger.dropus;


import android.support.v4.app.FragmentActivity;
import android.os.Bundle;


import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivityDriver extends FragmentActivity implements OnMapReadyCallback {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps_driver);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }




    @Override
    public void onMapReady(GoogleMap map) {







        map.addMarker(new MarkerOptions().position(new LatLng(30.720436, 76.831585)).title("Jaspreet singh").icon(BitmapDescriptorFactory.fromResource(R.drawable.mapsdriver1)));
        map.addMarker(new MarkerOptions().position(new LatLng(30.720861, 76.832163)).title("harit Taneja").icon(BitmapDescriptorFactory.fromResource(R.drawable.mapsdriver2)));

        map.addMarker(new MarkerOptions().position(new LatLng(30.720845, 76.832161)).title("Himanshi ").icon(BitmapDescriptorFactory.fromResource(R.drawable.mapsdriver3)));

        map.addMarker(new MarkerOptions().position(new LatLng(30.720570, 76.830374)).title("Himanshi").icon(BitmapDescriptorFactory.fromResource(R.drawable.mapsdriver3)));

        map.addMarker(new MarkerOptions().position(new LatLng(30.720244, 76.829841)).title("Dipesh").icon(BitmapDescriptorFactory.fromResource(R.drawable.mapsdriver5)));
        map.setMyLocationEnabled(true);






    }
}
