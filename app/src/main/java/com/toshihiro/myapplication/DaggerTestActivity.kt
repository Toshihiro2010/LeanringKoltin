package com.toshihiro.myapplication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import com.toshihiro.myapplication.myDagger.AwesomApplication
import dagger.android.DispatchingAndroidInjector
import javax.inject.Inject

class DaggerTestActivity : AppCompatActivity() {

    @Inject
    lateinit var awesomApplication: AwesomApplication

    @Inject
    lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dagger_test)
    }
}
