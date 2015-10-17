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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        markerHashMap = new HashMap<Marker, MyMarker>();
        GPSTracker gps = new GPSTracker(getApplicationContext());
        myMarker.add(new MyMarker("Kampus", "icon1",  gps.getLat(), gps.getLongi()));
        setUpMap();
        plotMarker(myMarker);
    }

    private void setUpMap()
    {
        if(nmap==null){
            nmap = ((SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.fragment)).getMap();

            if(nmap!=null){
                nmap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                    @Override
                    public boolean onMarkerClick(Marker marker) {
                        marker.showInfoWindow();
                        return true;
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
                nmap.setInfoWindowAdapter(new MarkerInfoWindowAdapter(getApplicationContext(), myMarker));
            }
        }
    }
}
