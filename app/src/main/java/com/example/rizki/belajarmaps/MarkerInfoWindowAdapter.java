package com.example.rizki.belajarmaps;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

/**
 * Created by wrismawan on 10/18/2015.
 */
public class MarkerInfoWindowAdapter implements GoogleMap.InfoWindowAdapter{
    private Context context;
    private MyMarker myMarker;
    public MarkerInfoWindowAdapter(Context context, MyMarker marker){
        this.context = context;
        this.myMarker = marker;
    }


    @Override
    public View getInfoWindow(Marker marker) {
        return null;
    }

    @Override
    public View getInfoContents(Marker marker) {
        LayoutInflater inf = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inf.inflate(R.layout.marker, null);
        ImageView img = (ImageView)v.findViewById(R.id.imgMark);
        TextView txt = (TextView)v.findViewById(R.id.txtMark);
        img.setImageResource(R.drawable.location_pin);
        txt.setText(myMarker.getLabel());
        return v;
    }
}
