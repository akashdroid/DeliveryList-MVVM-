
package com.airpay.airpaylendingapp.ui.googlemaps;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;


import com.airpay.airpaylendingapp.BR;
import com.airpay.airpaylendingapp.R;

import com.airpay.airpaylendingapp.databinding.ActivityMapsBinding;
import com.airpay.airpaylendingapp.ui.main.BlogActivity;
import com.airpay.airpaylendingapp.utils.base.BaseActivity;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.WeakHashMap;

import javax.inject.Inject;


public class GoogleMapsActivity extends BaseActivity<ActivityMapsBinding, MapsViewModel> implements MapsNavigator,OnMapReadyCallback {

    @Inject
    MapsViewModel mMapsViewModel;
    ActivityMapsBinding activityMapsBinding;
    private static final long ZOOM_LEVEL_STREET = 15;

    final WeakHashMap<String, Marker> mMarkers = new WeakHashMap<>();
    private GoogleMap mMap;
    private boolean isMapReady = false;
    String imageurl,desc,addr;
    Double lat,lng;
    int position;
    Toolbar mToolbar;

    /*public static Intent newIntent(Context context) {
        return new Intent(context, GoogleMapsActivity.class);
    }*/

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_maps;
    }

    @Override
    public MapsViewModel getViewModel() {
        return mMapsViewModel;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMapsBinding = getViewDataBinding();
        setUpToolbar();
        mMapsViewModel.setNavigator(this);
        getdata();

        initGoogleMap();
        startSeeding(imageurl,desc,addr,position);
    }

    private void startSeeding(String imageurl, String desc, String addr, int position) {
        mMapsViewModel.imageUrl.set(imageurl);
        mMapsViewModel.position.set(position);
        mMapsViewModel.author.set(addr);
        mMapsViewModel.title.set(desc);
    }

    private void getdata() {
        Intent intent = getIntent();
        imageurl=intent.getStringExtra("imageUrl");
        desc=intent.getStringExtra("description");
        addr=intent.getStringExtra("address");
        lat= intent.getDoubleExtra("lat",0);
        lng= intent.getDoubleExtra("lng",0);
        position=intent.getIntExtra("position",0);

    }
    public void setUpToolbar() {
        mToolbar = activityMapsBinding.toolbar;

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }



    void initGoogleMap() {
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(lat, lng);
        mMap.addMarker(new MarkerOptions().position(sydney).title(addr));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney,ZOOM_LEVEL_STREET));
        mMap.getUiSettings().setZoomControlsEnabled(false);
        mMap.getUiSettings().setZoomGesturesEnabled(false);
        mMap.getUiSettings().setAllGesturesEnabled(false);
        mMap.getUiSettings().setRotateGesturesEnabled(false);
    }
}
