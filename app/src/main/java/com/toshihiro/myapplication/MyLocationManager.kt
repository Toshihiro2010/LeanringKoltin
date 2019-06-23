package com.toshihiro.myapplication

import android.annotation.SuppressLint
import android.arch.lifecycle.LiveData
import android.content.Context
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.os.Looper

class MyLocationManager internal constructor(context: Context) : LiveData<Location>(), LocationListener {

    private val locationManager: LocationManager

    init {
        this.locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
    }

    @SuppressLint("MissingPermission")
    override fun onActive() {
        super.onActive()
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 10f, this, Looper.getMainLooper())
        locationManager.requestLocationUpdates(
            LocationManager.NETWORK_PROVIDER,
            1000,
            10f,
            this,
            Looper.getMainLooper()
        )
    }


    @SuppressLint("MissingPermission")
    override fun onInactive() {
        super.onInactive()
        locationManager.removeUpdates(this)
    }

    override fun onLocationChanged(location: Location) {
        value = location
    }

    override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {

    }

    override fun onProviderEnabled(provider: String) {

    }

    override fun onProviderDisabled(provider: String) {

    }
}
