package com.akshay.weatherapp.di.modules

import com.akshay.weatherapp.network.OpenWeatherApiInterface
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class WeatherAppModule {

    @Provides
    @Singleton
    fun providesProgrammingQuotesInterface(retrofit: Retrofit): OpenWeatherApiInterface {
        return retrofit.create(OpenWeatherApiInterface::class.java)
    }
}