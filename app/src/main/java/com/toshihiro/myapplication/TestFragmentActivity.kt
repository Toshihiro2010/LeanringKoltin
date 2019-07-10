package com.toshihiro.myapplication

import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.toshihiro.myapplication.Fragment.OneFragment
import kotlinx.android.synthetic.main.activity_test_fragment.*
import java.net.URI

class TestFragmentActivity : AppCompatActivity(), BlankFragment.OnFragmentInteractionListener {
    override fun onFragmentInteraction(uri: Uri) {

        Log.d("check" , "bent =>" + uri.toString())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_fragment)

        var fragment = BlankFragment.newInstance("test","test2")
        var uri : Uri = Uri.parse("https://www.google.co.th/")
        fragment.onButtonPressed(uri)
        supportFragmentManager.beginTransaction().replace(R.id.frameLayout,fragment).commit()

        btnTestFragment1.setOnClickListener {
            var fragment = OneFragment.newInstance("test")
//        supportFragmentManager.beginTransaction().replace(R.id.frameLayout,fragment).commit()
            supportFragmentManager.beginTransaction().add(R.id.frameLayout,fragment).commit()
        }

        btnTestFragment2.setOnClickListener {
            
        }

    }
}
