package com.akshay.weatherapp.ui.weeklyforecast

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.akshay.weatherapp.models.Location
import com.akshay.weatherapp.repositories.DailyForecastRepository
import kotlinx.coroutines.launch
import java.io.IOException
import java.lang.Exception
import javax.inject.Inject

class WeeklyForecastViewModel(private val forecastRepository: DailyForecastRepository) :
    ViewModel() {

    val dailyForecasts = forecastRepository.forecasts

    companion object {
        private const val TAG = "WeeklyForecastViewModel"
    }

    class Factory @Inject constructor(var forecastRepository: DailyForecastRepository) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return WeeklyForecastViewModel(forecastRepository) as T
        }
    }

    fun refreshForecasts(location: Location) {
        viewModelScope.launch {
            try {
                forecastRepository.refreshForecast(location)
            } catch (networkException: IOException) {
                //TODO: show error to UI
                Log.e(TAG, Log.getStackTraceString(networkException))
            } catch (ex: Exception) {
                Log.e(TAG, Log.getStackTraceString(ex))
            }

        }
    }
}