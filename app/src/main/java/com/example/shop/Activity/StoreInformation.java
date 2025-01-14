package com.example.shop.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;

import com.example.shop.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class StoreInformation extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    Toolbar toolBar_TT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_information);

        toolBar_TT = findViewById(R.id.actionBar_TT);
        ActionBar();

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    private void ActionBar() {
        setSupportActionBar(toolBar_TT);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolBar_TT.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
//        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            // TODO: Consider calling
//            //    ActivityCompat#requestPermissions
//            // here to request the missing permissions, and then overriding
//            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//            //                                          int[] grantResults)
//            // to handle the case where the user grants the permission. See the documentation
//            // for ActivityCompat#requestPermissions for more details.
//            return;
//        }
//
//        mMap.setMyLocationEnabled(true);
//
//
//        // Add a marker in Sydney and move the camera
//        LatLng myLocal = new LatLng(11.738856, 106.722761);
//        mMap.addMarker(new MarkerOptions().position(myLocal)
//                .title("UBND Xã Thanh An")
//                .snippet("Ấp An Hòa, Thanh An, Hớn Quản, Bình Phước, Việt Nam")
//                .icon(BitmapDescriptorFactory.defaultMarker()));
//        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

//        mMap.moveCamera(CameraUpdateFactory.newLatLng(myLocal));
//        LatLng sydney = new LatLng(-34, 151);
        LatLng myLocal = new LatLng(11.738818314942796, 106.72277624099492);
        mMap.addMarker(new MarkerOptions()
                .position(myLocal)
                .title("UBND Xã Thanh An")
                .icon(BitmapDescriptorFactory.defaultMarker())
        );
        mMap.moveCamera(CameraUpdateFactory.newLatLng(myLocal));
//        CameraPosition cameraPosition = new CameraPosition.Builder().target(myLocal).zoom(1000).build();
//        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }
}