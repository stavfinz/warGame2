package com.example.job_wargame2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class Entrance_window extends AppCompatActivity {

    private static final int REQUEST_CODE = 1;
    private Button entrance_BTN_startNewGame;
    private Button entrance_BTN_scoreTable;
    public static double latitude ;
    public static double longitude ;
    private LocationManager locationManager;
    private Location location;
    private final LocationListener locationListener = new LocationListener() {
        public void onLocationChanged(Location location) {
            latitude = location.getLongitude();
            longitude = location.getLatitude();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrance_window);
        findViews();
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.

            if (ActivityCompat.shouldShowRequestPermissionRationale(Entrance_window.this,
                    Manifest.permission.ACCESS_COARSE_LOCATION))  {
                // In an educational UI, explain to the user why your app requires this
                // permission for a specific feature to behave as expected. In this UI,
                // include a "cancel" or "no thanks" button that allows the user to
                // continue using your app without granting the permission.
                new AlertDialog.Builder(this)
                        .setTitle("Required Location Permission")
                        .setMessage("You have to give this permission to access GoogleMap feature")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ActivityCompat.requestPermissions(Entrance_window.this,
                                        new String[] { Manifest.permission.ACCESS_COARSE_LOCATION },
                                        REQUEST_CODE);
                            }
                        })
                        .create()
                        .show();
            } else {
                //Asking the permission for the first time
                // You can directly ask for the permission.
                ActivityCompat.requestPermissions(Entrance_window.this,
                        new String[] { Manifest.permission.ACCESS_COARSE_LOCATION },
                        REQUEST_CODE);
            }
            return;
        }
        //If the permission is already allowed
        location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        locationListener.onLocationChanged(location);
        Log.d("pttt","mouury"+latitude+", "+longitude);
        startNewGame();
        topTenWindow();

    }

    private void topTenWindow() {
        entrance_BTN_scoreTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(Entrance_window.this, scoreTable.class);
                startActivity(myIntent);
//                finish();
            }
        });
    }

    private void startNewGame() {
        entrance_BTN_startNewGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(Entrance_window.this, MainActivity.class);
                Log.d("pttt","mouury"+latitude+", "+longitude);
                myIntent.putExtra(MainActivity.LATITUDE, latitude);
                myIntent.putExtra(MainActivity.LONGITUDE, longitude);
                startActivity(myIntent);
//                finish();
            }
        });
    }

    private void findViews() {
        entrance_BTN_scoreTable = findViewById(R.id.entrance_BTN_scoreTable);
        entrance_BTN_startNewGame = findViewById(R.id.entrance_BTN_startNewGame);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == REQUEST_CODE){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){

                if (ContextCompat.checkSelfPermission(Entrance_window.this,
                        Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED){
                    location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                    locationListener.onLocationChanged(location);
                    startNewGame();
                    topTenWindow();
                }
            }else{
                latitude  = 0;
                longitude = 0;
                startNewGame();
                topTenWindow();
            }
        }
    }
}