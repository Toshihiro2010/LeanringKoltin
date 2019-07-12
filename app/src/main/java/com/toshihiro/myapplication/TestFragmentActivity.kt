package com.toshihiro.myapplication

import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.toshihiro.myapplication.Fragment.OneFragment
import com.toshihiro.myapplication.model.Profile
import kotlinx.android.synthetic.main.activity_test_fragment.*

class TestFragmentActivity : AppCompatActivity(), BlankFragment.OnFragmentInteractionListener {
    override fun onFragmentInteraction(uri: Uri) {

        Log.d("check" , "bent =>" + uri.toString())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_fragment)

//        var fragment = BlankFragment.newInstance("test","test2")
        var fragment = OneFragment.newInstance("test")
        var uri : Uri = Uri.parse("https://www.google.co.th/")
//        fragment.onButtonPressed(uri)
        if(savedInstanceState == null){
            supportFragmentManager.beginTransaction().replace(R.id.frameLayout,fragment).commit()
        }

        btnTestFragment1.setOnClickListener {
//            var fragment = OneFragment.newInstance("test")
            var fragment = BlankFragment.newInstance("test","test2")
//        supportFragmentManager.beginTransaction().replace(R.id.frameLayout,fragment).commit()
//            if(supportFragmentManager)
            supportFragmentManager.beginTransaction()
                .replace(R.id.frameLayout,fragment)
                .addToBackStack(null)
                .commit()
        }

        btnTestFragment2.setOnClickListener {
            supportFragmentManager.popBackStack()
        }

    }

    override fun onStart() {
        super.onStart()
        var test = Profile("Patipan", "Programmer", 24)
        Log.d("bent", test.name + " / " + test.job)

//        supportFragmentManager

    }
}
