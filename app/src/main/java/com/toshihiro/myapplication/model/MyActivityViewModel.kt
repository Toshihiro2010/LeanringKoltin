package com.toshihiro.myapplication.model

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.toshihiro.myapplication.service.GithubAPI
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


class MyActivityViewModel : ViewModel() {
//    private var userData: LiveData<User>? = null
//
//    fun getUser(userId: String): LiveData<User>? {
//        if (userData == null) {
//            userData = webservice.getUser(userId)
//        }
//        return userData
//    }

    private var userData : MutableLiveData<User>? = null

    fun getUser(username : String) : LiveData<User>{

        if (userData == null){
            userData = MutableLiveData()
            providesUserInfoAPIs().getUser(username)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({

                })
        }
        return userData as MutableLiveData<User>
    }

    fun providesUserInfoAPIs(): GithubAPI = Retrofit.Builder()
        .baseUrl("https://api.github.com/")
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(GithubAPI::class.java)
}