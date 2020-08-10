package com.akshay.weatherapp.ui.weeklyforecast

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.akshay.weatherapp.models.Location
import com.akshay.weatherapp.repositories.DailyForecastRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class WeeklyForecastViewModel(private val forecastRepository: DailyForecastRepository) :
    ViewModel() {

    val dailyForecasts = forecastRepository.forecasts

    class Factory @Inject constructor(var forecastRepository: DailyForecastRepository) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return WeeklyForecastViewModel(forecastRepository) as T
        }
    }

    fun refreshForecasts(location: Location) {
        viewModelScope.launch {
            forecastRepository.refreshForecast(location)
        }
    }
}