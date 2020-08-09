package com.akshay.weatherapp.di.components

import com.akshay.weatherapp.di.modules.AppModule
import com.akshay.weatherapp.di.modules.NetModule
import com.akshay.weatherapp.di.modules.WeatherAppModule
import com.akshay.weatherapp.ui.MainActivity
import dagger.Component
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, NetModule::class, WeatherAppModule::class])
interface AppComponent {

    fun inject(activity: MainActivity)

    fun retrofit(): Retrofit
    fun okHttpClient(): OkHttpClient
}