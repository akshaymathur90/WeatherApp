package com.akshay.weatherapp.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface DailyForecastDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(dailyForecast: DailyForecast)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(dailyForecasts: List<DailyForecast>)

    @Update
    fun update(dailyForecast: DailyForecast)

    @Query("Select * FROM daily_forecasts")
    fun getForecasts(): LiveData<List<DailyForecast>>

    @Query("Select * FROM daily_forecasts WHERE timestamp = :timestamp")
    fun getForecast(timestamp: Long): DailyForecast?
}