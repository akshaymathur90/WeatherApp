package com.akshay.weatherapp.ui.weeklyforecast

import android.util.Log
import androidx.lifecycle.*
import com.akshay.weatherapp.models.Errors
import com.akshay.weatherapp.models.Location
import com.akshay.weatherapp.repositories.DailyForecastRepository
import kotlinx.coroutines.launch
import java.io.IOException
import java.lang.Exception
import javax.inject.Inject

class WeeklyForecastViewModel(private val forecastRepository: DailyForecastRepository) :
    ViewModel() {

    val dailyForecasts = forecastRepository.forecasts

    private var _eventNetworkError = MutableLiveData<Errors>()
    val eventNetworkError: LiveData<Errors>
        get() = _eventNetworkError

    private var _isNetworkErrorShown = MutableLiveData<Boolean>()
    val isNetworkErrorShown: LiveData<Boolean>
        get() = _isNetworkErrorShown

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
                _isNetworkErrorShown.value = false
                _eventNetworkError.value = null
            } catch (networkException: IOException) {
                if (dailyForecasts.value.isNullOrEmpty()) {
                    _eventNetworkError.value = Errors.NETWORK_ERROR
                }
                Log.e(TAG, Log.getStackTraceString(networkException))
            } catch (ex: Exception) {
                Log.e(TAG, Log.getStackTraceString(ex))
                _eventNetworkError.value = Errors.UNKNOWN_ERROR
            }

        }
    }

    fun onNetworkErrorShown() {
        _isNetworkErrorShown.value = true
    }
}