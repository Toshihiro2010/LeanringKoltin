package com.toshihiro.myapplication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.toshihiro.myapplication.model.User
import com.toshihiro.myapplication.service.GithubAPI
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ViewTestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_test)
    }





}
