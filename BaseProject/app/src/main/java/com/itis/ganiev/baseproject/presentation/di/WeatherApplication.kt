package com.itis.ganiev.baseproject.presentation.di

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import moxy.MvpFacade

@HiltAndroidApp
class WeatherApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        MvpFacade.init()
    }
}
