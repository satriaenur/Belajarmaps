package com.example.rizki.belajarmaps;

import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.ClusterItem;

/**
 * Created by rizki on 10/17/15.
 */
public class MyMarker implements ClusterItem {
    private String label;
    private int icon;
    private double _lat;
    private double _long;

    public MyMarker(String label, int icon, double _lat, double _long){
        this.label = label;
        this.icon = icon;
        this._lat = _lat;
        this._long = _long;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public void set_lat(double _lat) {
        this._lat = _lat;
    }

    public void set_long(double _long) {
        this._long = _long;
    }

    public String getLabel() {
        return label;
    }

    public int getIcon() {
        return icon;
    }

    public double get_lat() {
        return _lat;
    }

    public double get_long() {
        return _long;
    }

    @Override
    public LatLng getPosition() {
        LatLng latlng = new LatLng(get_lat(),get_long());
        return latlng;
    }
}
