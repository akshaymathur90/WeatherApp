package com.akshay.weatherapp.models

import com.akshay.weatherapp.database.DailyForecast

data class Forecast(
    val lat: Float,
    val lon: Float,
    val timezone: String,
    val timezone_offset: Long,
    val daily: List<Daily>
)

data class Daily(
    val dt: Long,
    val sunrise: Long,
    val sunset: Long,
    val pressure: Float,
    val humidity: Float,
    val temp: Temp
)

data class Temp(val min: Float, val max: Float)

fun Forecast.toDatabaseModel(): List<DailyForecast> {
    val dbDailyForecastList = mutableListOf<DailyForecast>()
    val sortedDailyForecast = daily.sortedBy { it.dt }

    sortedDailyForecast.forEach { dayForecast ->
        val dbDailyForecast = DailyForecast(
            dayForecast.dt,
            timezone,
            timezone_offset,
            dayForecast.sunrise,
            dayForecast.sunset,
            dayForecast.pressure,
            dayForecast.humidity,
            dayForecast.temp.min,
            dayForecast.temp.max
        )

        dbDailyForecastList.add(dbDailyForecast)
    }

    return dbDailyForecastList
}