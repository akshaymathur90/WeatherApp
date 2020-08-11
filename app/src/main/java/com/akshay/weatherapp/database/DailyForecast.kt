package com.akshay.weatherapp.database

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "daily_forecasts")
data class DailyForecast(
    @PrimaryKey val timestamp: Long,
    @ColumnInfo(name = "timezone") val timezone: String,
    @ColumnInfo(name = "timezone_offset") val timezone_offset: Long,
    @ColumnInfo(name = "sunrise") val sunrise: Long,
    @ColumnInfo(name = "sunset") val sunset: Long,
    @ColumnInfo(name = "pressure") val pressure: Float,
    @ColumnInfo(name = "humidity") val humidity: Float,
    @ColumnInfo(name = "temp_min") val temp_min: Float,
    @ColumnInfo(name = "temp_max") val temp_max: Float
): Parcelable