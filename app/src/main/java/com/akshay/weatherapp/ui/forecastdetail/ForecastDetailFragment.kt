package com.akshay.weatherapp.ui.forecastdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.akshay.weatherapp.R
import com.akshay.weatherapp.database.DailyForecast
import com.akshay.weatherapp.utils.Utils
import kotlinx.android.synthetic.main.fragment_forecast_detail.*

class ForecastDetailFragment : Fragment() {

    companion object {
        const val TAG = "ForecastDetailFragment"
        private const val EXTRA_FORECAST = "EXTRA_FORECAST"
        fun newInstance(forecast: DailyForecast): ForecastDetailFragment {
            val fragment =
                ForecastDetailFragment()
            val bundle = Bundle()
            bundle.putParcelable(EXTRA_FORECAST, forecast)
            fragment.arguments = bundle
            return fragment
        }
    }

    private fun extractForecast(): DailyForecast? {
        return arguments?.getParcelable(EXTRA_FORECAST)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_forecast_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val forecast = extractForecast()

        forecast?.let {
            displayForecast(forecast)
        }
    }

    private fun displayForecast(forecast: DailyForecast) {
        tvTitle.text =
            Utils.formatDateTime(Utils.convertToLocalTime(forecast.timestamp, forecast.timezone))

        tvSunriseTime.text =
            Utils.formatTime(Utils.convertToLocalTime(forecast.sunrise, forecast.timezone))

        tvSunsetTime.text =
            Utils.formatTime(Utils.convertToLocalTime(forecast.sunset, forecast.timezone))

        tvPressureValue.text =
            getString(R.string.pressure_format_string, forecast.pressure.toInt().toString())

        tvHumidityValue.text =
            getString(R.string.humidity_format_string, forecast.humidity.toInt().toString())

    }
}