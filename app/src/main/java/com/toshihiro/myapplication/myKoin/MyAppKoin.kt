package com.toshihiro.myapplication.myKoin

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MyAppKoin : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyAppKoin)
            modules(appModule)
            androidLogger()
        }
    }
}