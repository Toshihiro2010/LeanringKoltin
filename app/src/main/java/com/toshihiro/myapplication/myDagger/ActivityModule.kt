package com.toshihiro.myapplication.myDagger

import com.toshihiro.myapplication.activity.DaggerTestActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector()
    abstract fun contributeMainActivity(): DaggerTestActivity
}