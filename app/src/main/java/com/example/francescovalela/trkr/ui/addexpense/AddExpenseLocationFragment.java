package com.example.francescovalela.trkr.ui.addExpense;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.francescovalela.trkr.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by francescovalela on 2017-03-16.
 */


//todo how to call this activity from button? button from xml to main activity then to this fragment
public class AddExpenseLocationFragment extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {

    TheListener listener;

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.d("god damn", "doesnt work");
    }

    //interface to pass values from fragment to activity
    interface TheListener{
        void returnLocation(String name, String address, double latitude, double longitude, String placeId);
    }

    private static final String TAG = "PlacePickerSample";
    private static final int PLACE_PICKER_REQUEST = 1;
    private GoogleApiClient mGoogleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mGoogleApiClient = new GoogleApiClient
                .Builder(this)
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                .enableAutoManage(this, this)
                .build();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    protected void onResume() {
        super.onResume();

        PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();

        try {
            startActivityForResult(builder.build(this), PLACE_PICKER_REQUEST);
        } catch (GooglePlayServicesRepairableException e1) {
            e1.printStackTrace();
        } catch (GooglePlayServicesNotAvailableException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PLACE_PICKER_REQUEST) {
            if (resultCode == Activity.RESULT_OK) {
                Place selectedPlace = PlacePicker.getPlace(this, data);
                final String name = selectedPlace.getName().toString();
                final String address = selectedPlace.getAddress().toString();
                final String placeId = selectedPlace.getId();
                final double latitude = selectedPlace.getLatLng().latitude;
                final double longitude = selectedPlace.getLatLng().longitude;

                if (listener != null)
                {
                    listener.returnLocation(name, address, latitude, longitude, placeId);
                }
            }

        }
    }
}
