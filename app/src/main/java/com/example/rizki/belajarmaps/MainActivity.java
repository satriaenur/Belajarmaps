package com.example.rizki.belajarmaps;

import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends FragmentActivity implements OnMapReadyCallback{

    private GoogleMap nmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SupportMapFragment map = (SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.fragment);
        map.getMapAsync(this);

        nmap = map.getMap();
        nmap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener(){

            @Override
            public boolean onMarkerClick(Marker marker) {
                marker.showInfoWindow();
                return true;
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap map){
        GPSTracker gps = new GPSTracker(this);
        map.setMyLocationEnabled(true);
        map.getUiSettings().setZoomControlsEnabled(true);
        map.getUiSettings().setZoomGesturesEnabled(true);
        if(!gps.canGetLocation()){
            gps.showSettingAlert();
        }else {
            Log.e("test", gps.getLat() + "");
            Log.e("test", gps.getLongi() + "");
            LatLng mapCenter = new LatLng(gps.getLat(), gps.getLongi());
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(mapCenter, 17));

            map.addMarker(new MarkerOptions()
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.location_pin))
                    .position(mapCenter)
                    .flat(true)
                    .rotation(245));
            CameraPosition cameraPosition = CameraPosition.builder()
                    .target(mapCenter)
                    .zoom(17)
                    .bearing(90)
                    .build();

            map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition),2000,null);

        }
    }
}
