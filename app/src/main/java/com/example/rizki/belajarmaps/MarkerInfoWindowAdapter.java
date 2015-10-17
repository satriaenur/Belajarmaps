package com.example.rizki.belajarmaps;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

import java.util.HashMap;

/**
 * Created by wrismawan on 10/18/2015.
 */
public class MarkerInfoWindowAdapter implements GoogleMap.InfoWindowAdapter{
    private Context context;
    private HashMap<Marker, MyMarker> markers;
    public MarkerInfoWindowAdapter(Context context, HashMap<Marker, MyMarker> marker){
        this.context = context;
        this.markers = marker;
    }


    @Override
    public View getInfoWindow(Marker marker) {
        return null;
    }

    @Override
    public View getInfoContents(Marker marker) {
        LayoutInflater inf = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inf.inflate(R.layout.marker, null);
        MyMarker myMarker = markers.get(marker);
        ImageView img = (ImageView)v.findViewById(R.id.imgMark);
        TextView txt = (TextView)v.findViewById(R.id.txtMark);
        img.setImageResource(myMarker.getIcon());
        txt.setText(myMarker.getLabel());
        return v;
    }
}
