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

class MyLocationManagerOld : LifecycleObserver, LocationListener {

    private var enable: Boolean = false
    private var lifecycle: Lifecycle? = null
    private var listener: MylocationListener? = null
    private var locationManager: LocationManager? = null

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
        if (location != null) {
            listener?.onLocationChange(location)
        }
    }

    override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onProviderEnabled(provider: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onProviderDisabled(provider: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}