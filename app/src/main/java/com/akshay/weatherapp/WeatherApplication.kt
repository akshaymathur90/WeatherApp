package com.akshay.weatherapp

import android.app.Application
import com.akshay.weatherapp.di.components.AppComponent
import com.akshay.weatherapp.di.components.DaggerAppComponent
import com.akshay.weatherapp.di.modules.AppModule
import com.akshay.weatherapp.di.modules.NetModule
import com.facebook.stetho.Stetho

class WeatherApplication: Application() {

    lateinit var appComponent: AppComponent
    private val baseUrl = "https://api.openweathermap.org/data/2.5/"
    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this)

        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .netModule(NetModule(baseUrl))
            .build()
    }
}