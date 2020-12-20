package com.example.job_wargame2;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Fragment_Map extends Fragment implements OnMapReadyCallback {
    private GoogleMap mapAPI;
    public MapView mMapView;
    private double latitude;
    private double longitude;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_map,container,false);
        findViews(view);
        initViews();
        return view;
    }

    private void findViews(View view) {
        mMapView = view.findViewById(R.id.mapView);
    }

    private void initViews() {
        if(mMapView != null){
            mMapView.onCreate(null);
            mMapView.onResume();
            mMapView.getMapAsync(this);
        }
    }
    public void showLocation(double latitude,double longitude){
        this.latitude = latitude;
        this.longitude = longitude;
        LatLng location = new LatLng(latitude, longitude);
        mapAPI.addMarker(new MarkerOptions().position(location).title("CurrentLocation"));
        mapAPI.moveCamera(CameraUpdateFactory.newLatLng(location));
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        MapsInitializer.initialize(getContext());
        mapAPI = googleMap;

    }
    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }
}
