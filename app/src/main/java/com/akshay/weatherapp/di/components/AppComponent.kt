package com.akshay.weatherapp.di.components

import com.akshay.weatherapp.di.modules.AppModule
import com.akshay.weatherapp.di.modules.DatabaseModule
import com.akshay.weatherapp.di.modules.NetModule
import com.akshay.weatherapp.di.modules.WeatherAppModule
import com.akshay.weatherapp.ui.MainActivity
import com.akshay.weatherapp.ui.weeklyforecast.WeeklyForecastFragment
import dagger.Component
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, NetModule::class, WeatherAppModule::class, DatabaseModule::class])
interface AppComponent {

    fun inject(activity: MainActivity)
    fun inject(fragment: WeeklyForecastFragment)

    fun retrofit(): Retrofit
    fun okHttpClient(): OkHttpClient
}