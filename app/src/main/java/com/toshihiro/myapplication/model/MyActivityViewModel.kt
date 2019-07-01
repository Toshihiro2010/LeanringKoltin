package com.toshihiro.myapplication.model

import android.annotation.SuppressLint
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.toshihiro.myapplication.service.GithubAPI
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


class MyActivityViewModel : ViewModel() {

    private var userData : MutableLiveData<User>? = null

    @SuppressLint("CheckResult")
    fun getUser(username : String) : LiveData<User>{

        if (userData == null){
            userData = MutableLiveData()
            providesUserInfoAPIs().getUser(username)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    if(it.isSuccessful){
                        userData?.value = it.body()
                    }else{
                        Log.d("Bent" , "Error GetUser")
                    }
                }
        }
        return userData as MutableLiveData<User>
    }

    fun clear() : LiveData<User>{
        userData?.value = null
        return  userData as MutableLiveData<User>
    }

    fun providesUserInfoAPIs(): GithubAPI = Retrofit.Builder()
        .baseUrl("https://api.github.com/")
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(GithubAPI::class.java)
}