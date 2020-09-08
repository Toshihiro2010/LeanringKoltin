package com.toshihiro.myapplication.activity

import android.annotation.SuppressLint
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Toast
import com.toshihiro.myapplication.BlankFragment
import com.toshihiro.myapplication.Fragment.OneFragment
import com.toshihiro.myapplication.Fragment.TwoFragment
import com.toshihiro.myapplication.MyListener.MyFragmentListener
import com.toshihiro.myapplication.R
import com.toshihiro.myapplication.model.Profile
import kotlinx.android.synthetic.main.activity_test_fragment.*

class TestFragmentActivity : AppCompatActivity(), BlankFragment.OnFragmentInteractionListener, MyFragmentListener{

    var strBtnCenter: String? = null


    override fun onButtonOkClick(message: String) {
        strBtnCenter = message
        btnCenter.text = strBtnCenter
        val oneFragment = OneFragment.newInstance("bent")
        supportFragmentManager.beginTransaction().add(R.id.frameLayout, oneFragment)
            .addToBackStack(null)
            .commit()


    }


    override fun onButtonCloseClick() {
        Log.d("Bent => " , "onButtonCloseClick")
        supportFragmentManager.popBackStack()

    }

    override fun onFragmentInteraction(uri: Uri) {

        Log.d("check" , "bent =>" + uri.toString())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_fragment)
        strBtnCenter = btnCenter.text.toString()
//        var fragment = BlankFragment.newInstance("test","test2")
        var fragment = TwoFragment.newInstance()
        var uri : Uri = Uri.parse("https://www.google.co.th/")
//        fragment.onButtonPressed(uri)
        if(savedInstanceState == null){
            supportFragmentManager.beginTransaction().replace(R.id.frameLayout,fragment).commit()
        }

        btnTestFragment1.setOnClickListener {
//            var fragment = OneFragment.newInstance("test")
            var fragment = BlankFragment.newInstance("test", "test2")
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

        btnCenter.setOnClickListener {
            val count = supportFragmentManager.backStackEntryCount
            Log.d("bent => " , "count: $count")
        }

    }

    override fun onStart() {
        super.onStart()
        var test = Profile("Patipan", "Programmer", 24)
        Log.d("bent", test.name + " / " + test.job)

//        supportFragmentManager

    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putString("key_bent",btnCenter.text.toString())
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        if (savedInstanceState != null) {
            strBtnCenter = savedInstanceState.getString("key_bent","-1")
//            if(strBtnCenter != null){
//                Toast.makeText(this,"data : " + strBtnCenter , Toast.LENGTH_SHORT).show()
//            }
            btnCenter.text = strBtnCenter
        }
    }

    private var doubleBackToExitPressedOnce = false
    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount == 0) {
            if (doubleBackToExitPressedOnce) {
                super.onBackPressed()
                return
            }

            doubleBackToExitPressedOnce = true
            Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show()

            Handler().postDelayed(Runnable {
                doubleBackToExitPressedOnce = false
            }, 2000)
            return
        }
        super.onBackPressed()

    }


}
