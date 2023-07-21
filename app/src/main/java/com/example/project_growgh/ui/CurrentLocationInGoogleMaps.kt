package com.example.project_growgh.ui

import android.content.pm.PackageManager
import android.location.Location
import androidx.fragment.app.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.app.ActivityCompat
import com.example.project_growgh.R
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class CurrentLocationInGoogleMaps : Fragment(), OnMapReadyCallback {
    private lateinit var currentLocation: Location
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient

    private var permissionCode = 101

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(
            R.layout.fragment_current_location_in_google_maps2,
            container,
            false
        )

        v.apply {
        }

        return v
    }

    private fun getUserCurrentLocation() {
        if (this.context?.let {
                ActivityCompat.checkSelfPermission(
                    it,
                    android.Manifest.permission.ACCESS_FINE_LOCATION
                )
            }
            != PackageManager.PERMISSION_GRANTED && this.let {
                this.context?.let { it1 ->
                    ActivityCompat.checkSelfPermission(
                        it1, android.Manifest.permission.ACCESS_FINE_LOCATION
                    )
                }
            } != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this.requireActivity(),
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                permissionCode
            )

            return
        }

        val getLocation =
            fusedLocationProviderClient.lastLocation.addOnSuccessListener { location ->
                if (location != null) {
                    currentLocation = location
                }
            }


    }

    private val callback = OnMapReadyCallback { googleMap ->
        fusedLocationProviderClient =
            LocationServices.getFusedLocationProviderClient(this.requireActivity())

        val currentLocation = LatLng(0.0, 0.0)
        googleMap.addMarker(MarkerOptions().position(currentLocation).title("CurrentLocation"))
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(currentLocation))
    }

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
//        mapFragment?.getMapAsync(callback)
//    }

    override fun onMapReady(p0: GoogleMap) {
        getUserCurrentLocation()

        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }
}