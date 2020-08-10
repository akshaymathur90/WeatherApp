package com.akshay.weatherapp.di.modules

import android.app.Application
import androidx.room.Room
import com.akshay.weatherapp.database.DailyForecastDao
import com.akshay.weatherapp.database.WeatherDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun providesQuotesDatabase(application: Application): WeatherDatabase {
        return Room.databaseBuilder(application, WeatherDatabase::class.java, "weather-db").build()
    }

    @Singleton
    @Provides
    fun providesQuotesDao(quotesDatabase: WeatherDatabase) : DailyForecastDao {
        return quotesDatabase.getDailyForecastDao()
    }
}