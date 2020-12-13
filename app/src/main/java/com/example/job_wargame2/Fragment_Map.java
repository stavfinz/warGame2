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
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Fragment_Map extends Fragment implements OnMapReadyCallback {
    private TextView map_LBL_name;
    private GoogleMap mapAPI;
    public SupportMapFragment mapFragment;
    private double latitude;
    private double longitude;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_map,container,false);
        findviews(view);
        initViews();
        return view;
    }

    private void findviews(View view) {
        map_LBL_name= view.findViewById(R.id.map_LBL_name);

    }

    private void initViews() {


    }
    public void showLocation(double latitude,double longitude){
        map_LBL_name.setText("Latitude: "+latitude+" ,Longitude: "+longitude);
        this.latitude = latitude;
        this.longitude = longitude;
        Log.d("pttt", (String) map_LBL_name.getText());


    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mapAPI = googleMap;
        LatLng currentLocation = new LatLng(latitude,longitude);
        Log.d("pttt","latitude : "+latitude+" logitude :"+longitude);
        mapAPI.addMarker(new MarkerOptions().position(currentLocation).title("CurrentLocation"));
        mapAPI.moveCamera(CameraUpdateFactory.newLatLng(currentLocation));

    }
}
