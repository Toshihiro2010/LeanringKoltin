package com.toshihiro.myapplication

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.view.View
import android.widget.Toast
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener
import com.karumi.dexter.listener.single.SnackbarOnDeniedPermissionListener
import kotlinx.android.synthetic.main.activity_main.*
import java.net.URI

class MainActivity : AppCompatActivity() {


    private var myLocationManagerOld: MyLocationManagerOld? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button1.setOnClickListener {
            Toast.makeText(this,"Helllo" , Toast.LENGTH_LONG).show()
        }

        button2.setOnClickListener{
            Toast.makeText(this,"HellloJAJAJA" , Toast.LENGTH_LONG).show()
        }

//        myLocationManagerOld =MyLocationManagerOld(this,this.lifecycle,)

        Dexter.withActivity(this)
            .withPermission(Manifest.permission.ACCESS_FINE_LOCATION)
            .withListener(object : PermissionListener {
                override fun onPermissionGranted(response: PermissionGrantedResponse?) {
                    Toast.makeText(applicationContext,"grant : " + response.toString() , Toast.LENGTH_LONG).show()
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
                            startActivity(intent)

                        }else{
                            Toast.makeText(applicationContext,"denied : NotPermanentlyDenied" , Toast.LENGTH_LONG).show()
                        }
                    }
                }

            }).check()


    }



    override fun onStart() {
        super.onStart()

    }


}
