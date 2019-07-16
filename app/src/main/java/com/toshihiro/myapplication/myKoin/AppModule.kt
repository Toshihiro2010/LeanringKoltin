package com.toshihiro.myapplication.myKoin


import com.toshihiro.myapplication.myDagger.UserPreference
import com.toshihiro.myapplication.service.RetrofitClient
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val appModule = module {
    single { UserPreference(androidContext()) }
    single { RetrofitClient().getRetrofitClient()}
}