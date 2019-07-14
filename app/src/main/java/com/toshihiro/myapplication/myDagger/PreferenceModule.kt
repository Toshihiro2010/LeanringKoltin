package com.toshihiro.myapplication.myDagger

import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class PreferenceModule {

    @Singleton
    @Provides
    fun provideUserPreference(application: AwesomApplication) = UserPreference(application.applicationContext)


//    @Singleton
//    @Provides
//    fun proiveAwesoneManager(userPreference: UserPreference) = AwesomApplication(userPreference)
}