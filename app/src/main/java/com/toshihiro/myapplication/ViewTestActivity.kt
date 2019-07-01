package com.toshihiro.myapplication

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.toshihiro.myapplication.databinding.ActivityViewTestBinding
import com.toshihiro.myapplication.model.MyActivityViewModel
import kotlinx.android.synthetic.main.activity_view_test.*

class ViewTestActivity : AppCompatActivity() {

    var strData :String ="Toshihiro2010"

    var binding : ActivityViewTestBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_view_test)

        binding = DataBindingUtil.setContentView(this,R.layout.activity_view_test)

        ViewModelProviders.of(this)
            .get(MyActivityViewModel::class.java)
            .getUser(strData)
            .observe(this, Observer {
                binding?.userInfo = it
            })

        tvButtonTest.setOnClickListener {
//            binding?.userInfo?.login = "Bent2010"
            var viewModel = ViewModelProviders.of(this).get(MyActivityViewModel::class.java)
            viewModel.clear().observe(this, Observer {
                binding?.userInfo = it
            })

        }
    }
}
