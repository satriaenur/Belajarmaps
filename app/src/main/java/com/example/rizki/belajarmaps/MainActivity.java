package com.example.rizki.belajarmaps;

import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends FragmentActivity{

    private GoogleMap nmap;
    private HashMap<Marker, MyMarker> markerHashMap;
    private ArrayList<MyMarker> myMarker = new ArrayList<MyMarker>();
    private int a=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        markerHashMap = new HashMap<Marker, MyMarker>();
        GPSTracker gps = new GPSTracker(getApplicationContext());
        myMarker.add(new MyMarker("Your Location\n"+gps.getLat()+", "+gps.getLongi(), R.drawable.student,  gps.getLat(), gps.getLongi()));
        myMarker.add(new MyMarker("BEC\n-6.908238, 107.6066852", R.drawable.electric,  Double.parseDouble("-6.908238"), Double.parseDouble("107.6066852")));
        setUpMap(gps);
        plotMarker(myMarker);
    }

    private void setUpMap(GPSTracker gps)
    {
        if(nmap==null){
            nmap = ((SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.fragment)).getMap();

            if(nmap!=null){
                nmap.setMyLocationEnabled(true);
                nmap.getUiSettings().setZoomControlsEnabled(true);
                nmap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(gps.getLat(), gps.getLongi())));
                nmap.animateCamera(CameraUpdateFactory.zoomTo(10));
                nmap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                    @Override
                    public boolean onMarkerClick(Marker marker) {
                        marker.showInfoWindow();
                        return true;
                    }
                });
                nmap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
                    @Override
                    public void onMapLongClick(LatLng latLng) {
                        Marker marker = nmap.addMarker(new MarkerOptions().position(latLng).title("Long Pressed").icon(BitmapDescriptorFactory.fromResource(R.drawable.pin)));
                        markerHashMap.put(marker, new MyMarker("Pinned-"+a+"\n"+latLng.latitude+", "+latLng.longitude,R.drawable.pin,latLng.latitude, latLng.longitude));
                        a++;
                        nmap.setInfoWindowAdapter(new MarkerInfoWindowAdapter(getApplicationContext(), markerHashMap));
                    }
                });
            }
        }
        else Toast.makeText(getApplicationContext(), "Unable to create map", Toast.LENGTH_SHORT).show();
    }

    private void plotMarker(ArrayList<MyMarker> markers)
    {
        if(markers.size()>0){
            for(MyMarker myMarker : markers)
            {
                MarkerOptions markerOptions =  new MarkerOptions().position(new LatLng(myMarker.get_lat(),myMarker.get_long()));
                markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.location_pin));
                Marker currentMarker = nmap.addMarker(markerOptions);
                markerHashMap.put(currentMarker,myMarker);
                nmap.setInfoWindowAdapter(new MarkerInfoWindowAdapter(getApplicationContext(), markerHashMap));
            }
        }
    }
}
