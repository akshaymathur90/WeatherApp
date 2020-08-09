package com.akshay.weatherapp.models

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