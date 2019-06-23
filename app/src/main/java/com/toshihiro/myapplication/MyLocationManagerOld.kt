package com.toshihiro.myapplication

import android.annotation.SuppressLint
import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.OnLifecycleEvent
import android.content.Context
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.os.Looper
import android.util.Log

class MyLocationManagerOld : LifecycleObserver, LocationListener {

    var enable: Boolean = false
    var lifecycle: Lifecycle? = null
    var listener: MylocationListener? = null
    var locationManager: LocationManager? = null

    interface MylocationListener{
        fun onLocationChange(location : Location)
    }

    constructor(
        context: Context,
        lifecycle: Lifecycle,
        listener: MylocationListener
    ) {
        this.listener = listener
        this.lifecycle = lifecycle
        this.locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager?
        lifecycle.addObserver(this)

    }


    fun locationEnable(){
        Log.d("bent","location enable")
        if(!enable){
            enable = true
            start()
        }
    }

    @SuppressLint("MissingPermission")
    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun start(){
        if(enable){
            locationManager?.requestLocationUpdates(LocationManager.GPS_PROVIDER,1000,10f,this, Looper.getMainLooper())
            locationManager?.requestLocationUpdates(
                LocationManager.NETWORK_PROVIDER,
                1000,
                10f,
                this,
                Looper.getMainLooper()
            )
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun stop(){
        locationManager?.removeUpdates(this)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun cleanUp(){
        locationManager?.removeUpdates(this)
    }



    override fun onLocationChanged(location: Location?) {
        Log.d("Bent location","onChage")
        if (location != null) {
            listener?.onLocationChange(location)
        }
    }

    override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {

    }

    override fun onProviderEnabled(provider: String?) {

    }

    override fun onProviderDisabled(provider: String?) {

    }
}