package com.akshay.weatherapp.di.modules

import com.akshay.weatherapp.database.WeatherDatabase
import com.akshay.weatherapp.network.OpenWeatherApiInterface
import com.akshay.weatherapp.repositories.DailyForecastRepository
import com.akshay.weatherapp.ui.weeklyforecast.WeeklyForecastViewModel
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class WeatherAppModule {

    @Provides
    @Singleton
    fun providesOpenWeatherApiInterface(retrofit: Retrofit): OpenWeatherApiInterface {
        return retrofit.create(OpenWeatherApiInterface::class.java)
    }

    @Provides
    @Singleton
    fun providesDailyForecastRepository(
        weatherDatabase: WeatherDatabase,
        openWeatherApiInterface: OpenWeatherApiInterface
    ): DailyForecastRepository {
        return DailyForecastRepository(weatherDatabase, openWeatherApiInterface)
    }

    @Provides
    @Singleton
    fun providesWeeklyForecastViewModelFactory(dailyForecastRepository: DailyForecastRepository): WeeklyForecastViewModel.Factory {
        return WeeklyForecastViewModel.Factory(dailyForecastRepository)
    }
}