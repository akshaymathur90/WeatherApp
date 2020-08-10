package com.akshay.weatherapp.repositories

import androidx.lifecycle.LiveData
import com.akshay.weatherapp.database.DailyForecast
import com.akshay.weatherapp.database.WeatherDatabase
import com.akshay.weatherapp.models.Location
import com.akshay.weatherapp.models.toDatabaseModel
import com.akshay.weatherapp.network.NetworkUtils
import com.akshay.weatherapp.network.OpenWeatherApiInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DailyForecastRepository @Inject constructor(
    var weatherDatabase: WeatherDatabase,
    var openWeatherApiInterface: OpenWeatherApiInterface
) {

    val forecasts : LiveData<List<DailyForecast>> = weatherDatabase.getDailyForecastDao().getForecasts()

    suspend fun refreshForecast(location: Location) {
        val queryParams = NetworkUtils.getParams(location)
        withContext(Dispatchers.IO) {
            // fetch from retrofit
            val forecast = openWeatherApiInterface.getForecast(queryParams).await()
            val dailyForecasts = forecast.toDatabaseModel()
            // insert into DB
            weatherDatabase.getDailyForecastDao().insert(dailyForecasts)
        }
    }
}