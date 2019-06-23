package com.toshihiro.myapplication

import android.Manifest
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.karumi.dexter.Dexter
import kotlinx.android.synthetic.main.activity_main.*
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener



class MainLifecycleActivity : AppCompatActivity() {


    private var myLocationManagerOld: MyLocationManagerOld? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button1.setOnClickListener {
            Toast.makeText(this,"Helllo" , Toast.LENGTH_LONG).show()
        }

        button2.setOnClickListener(View.OnClickListener {
            Toast.makeText(this,"HellloJAJAJA" , Toast.LENGTH_LONG).show()
        })


    }

    override fun onStart() {
        super.onStart()



    }


}
