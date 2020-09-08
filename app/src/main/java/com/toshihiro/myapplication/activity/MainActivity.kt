package com.toshihiro.myapplication.activity

import android.Manifest
import android.content.Intent
import android.location.Location
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.widget.Toast
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import com.karumi.dexter.listener.single.PermissionListener
import kotlinx.android.synthetic.main.activity_main.*
import android.arch.lifecycle.Observer
import com.toshihiro.myapplication.MyLocationLiveDataListener
import com.toshihiro.myapplication.MyLocationManagerOld


class MainActivity : AppCompatActivity() {


    var myLocationManagerOld: MyLocationManagerOld? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.toshihiro.myapplication.R.layout.activity_main)

        button1.setOnClickListener {
            Toast.makeText(this,"Helllo" , Toast.LENGTH_LONG).show()
        }

        button2.setOnClickListener{
            val intent : Intent = Intent(this, DataActivity::class.java)
            startActivity(intent)
//            Toast.makeText(this,"HellloJAJAJA" , Toast.LENGTH_LONG).show()
        }

//        testLocation()
//        myLocationManagerOld = MyLocationManagerOld(this, lifecycle, getListenerMyLocation())
//        requestSinglePermission()
//        testLocation()
//        testssss()


    }

    override fun onStart() {
        super.onStart()
        testssss()
    }

    fun testssss(){

        var locationLiveDataListener = MyLocationLiveDataListener(this)

        locationLiveDataListener.observe(this, Observer<Location> { t ->
            Log.d("bent", "testss : " + t?.latitude + " / " + t?.longitude)
            var strLocation: String = "Bent\nlatitude : ${t?.latitude}\nlongitude : ${t?.longitude}"
            textView.text = strLocation
        })

//        Log.d("bent","testss : " + valuessss?.latitude + " / " + valuessss?.longitude)
    }


    fun requestSinglePermission(){
        myLocationManagerOld =
            MyLocationManagerOld(this, lifecycle, getListenerMyLocation())

        Dexter.withActivity(this)
            .withPermission(Manifest.permission.ACCESS_FINE_LOCATION)
            .withListener(object : PermissionListener {
                override fun onPermissionGranted(response: PermissionGrantedResponse?) {
                    myLocationManagerOld?.locationEnable()
                    Log.d("bent", "Permission success")

                }

                override fun onPermissionRationaleShouldBeShown(
                    permission: PermissionRequest?,
                    token: PermissionToken?
                ) {
                    token?.continuePermissionRequest()
                }

                override fun onPermissionDenied(response: PermissionDeniedResponse?) {
                    if (response != null) {
                        if(response.isPermanentlyDenied){
                            val intent : Intent = Intent()
                            intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                            var uri: Uri? = Uri.fromParts("package",applicationContext.packageName,null)
                            intent.data = uri
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                            startActivity(intent)

                        }else{
                            Toast.makeText(applicationContext,"denied : NotPermanentlyDenied" , Toast.LENGTH_LONG).show()
                        }
                    }
                }

            })
            .check()
        //end
    }

    fun requestMultiplePermission(){
        Dexter.withActivity(this)
            .withPermissions(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.CAMERA)
            .withListener(object : MultiplePermissionsListener {
                override fun onPermissionsChecked(report: MultiplePermissionsReport?) {
                    val permissionGrantedResponses = report?.getGrantedPermissionResponses()
                    val permissionDeniedResponses = report?.getDeniedPermissionResponses()

                    Log.d("bent 1: ", permissionGrantedResponses.toString())
                    Log.d("bent 2: ", permissionDeniedResponses.toString())

                    if (permissionGrantedResponses != null) {
                        for (grantedResponse in permissionGrantedResponses) {
                            grantedResponse.permissionName
                            Log.d("bent for: ", grantedResponse.permissionName + "")
                        }
                    }

                    report?.areAllPermissionsGranted()
                    Log.d("bent status: ",  report?.areAllPermissionsGranted().toString() + "")


                }

                override fun onPermissionRationaleShouldBeShown(
                    permissions: MutableList<PermissionRequest>?,
                    token: PermissionToken?
                ) {
                    token?.continuePermissionRequest()
                    

                }

            })
            .check()
    }


    private fun getListenerMyLocation(): MyLocationManagerOld.MylocationListener {
        return object : MyLocationManagerOld.MylocationListener {
            override fun onLocationChange(location: Location) {
                Log.d("bent location", "" + location.latitude + " / " + location.longitude)
                var strLocation: String =
                    "latitude : ${location.latitude}\nlongitude : ${location.longitude}"
                textView.text = strLocation

            }
        }
    }

}
