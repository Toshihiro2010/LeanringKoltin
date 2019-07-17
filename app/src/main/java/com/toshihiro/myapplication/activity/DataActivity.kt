package com.toshihiro.myapplication.activity

import android.Manifest
import android.arch.lifecycle.Observer
import android.content.Intent
import android.location.Location
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.widget.Toast
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener
import com.toshihiro.myapplication.MyLocationLiveDataListener
import com.toshihiro.myapplication.R
import kotlinx.android.synthetic.main.activity_data.*

class DataActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data)

        buttonBack.setOnClickListener{
//            finish()
            val intent : Intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        requestSinglePermission()
    }

    fun requestSinglePermission(){
//        myLocationManagerOld = MyLocationManagerOld(this, lifecycle, getListenerMyLocation())
//        var tvDatass : TextView = findViewById(R.id.tvData)
        Dexter.withActivity(this)
                .withPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                .withListener(object : PermissionListener {
                    override fun onPermissionGranted(response: PermissionGrantedResponse?) {

                        var locationLiveDataListener: MyLocationLiveDataListener =
                            MyLocationLiveDataListener(applicationContext)
                        locationLiveDataListener.observe(this@DataActivity,object : Observer<Location> {
                            override fun onChanged(t: Location?) {
                                Log.d("bent location", "" + t?.latitude + " / " + t?.longitude)
                                var strLocation :String = "latitude : ${t?.latitude}\nlongitude : ${t?.longitude}"
                                tvData.text = strLocation
                            }

                        })
                        Log.d("bent", "Permission success")

                    }

                    override fun onPermissionRationaleShouldBeShown(
                            permission: PermissionRequest?,
                            token: PermissionToken?
                    ) {
                        if (token != null) {
                            token.continuePermissionRequest()
                        }
                    }

                    override fun onPermissionDenied(response: PermissionDeniedResponse?) {
                        if (response != null) {
                            if(response.isPermanentlyDenied){
                                val intent : Intent = Intent()
                                intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                                var uri: Uri? = Uri.fromParts("package",applicationContext.packageName,null)
                                intent.setData(uri)
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
}
