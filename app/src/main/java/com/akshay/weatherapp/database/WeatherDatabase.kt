package com.akshay.weatherapp.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [DailyForecast::class], version = 1, exportSchema = false)
abstract class WeatherDatabase: RoomDatabase() {

    abstract fun getDailyForecastDao(): DailyForecastDao
}