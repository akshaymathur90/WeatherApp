package com.akshay.weatherapp.network

import com.akshay.weatherapp.models.Forecast
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface OpenWeatherApiInterface {

    @GET("onecall")
    fun getForecast(@QueryMap queryMap: Map<String, String>): Call<Forecast>
}