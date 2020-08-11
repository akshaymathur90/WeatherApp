package com.akshay.weatherapp.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.akshay.weatherapp.R
import com.akshay.weatherapp.WeatherApplication
import com.akshay.weatherapp.database.DailyForecast
import com.akshay.weatherapp.models.Forecast
import com.akshay.weatherapp.network.OpenWeatherApiInterface
import com.akshay.weatherapp.ui.forecastdetail.ForecastDetailFragment
import com.akshay.weatherapp.ui.weeklyforecast.WeeklyForecastFragment
import com.akshay.weatherapp.ui.weeklyforecast.WeeklyForecastViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class MainActivity : AppCompatActivity(), WeeklyForecastFragment.ForecastItemClickListener {

    @Inject
    lateinit var openWeatherApiInterface: OpenWeatherApiInterface

    @Inject
    lateinit var weeklyForecastViewModelFactory: WeeklyForecastViewModel.Factory

    companion object {
        private const val TAG = "MainActivity"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        (application as WeatherApplication).appComponent.inject(this)
        supportFragmentManager.addOnBackStackChangedListener {
            if (supportFragmentManager.backStackEntryCount ==0) {
                finish()
            }
        }
        init()
    }

    private fun init() {
        val fragment = WeeklyForecastFragment.newInstance()
        showFragment(fragment, WeeklyForecastFragment.TAG)
    }

    private fun showFragment(fragment: Fragment, tag: String) {
        supportFragmentManager.beginTransaction()
            .add(R.id.content, fragment, tag)
            .addToBackStack(tag)
            .commit()
    }

    override fun onItemClick(forecast: DailyForecast) {
        val forecastDetailFragment = ForecastDetailFragment.newInstance(forecast)
        showFragment(forecastDetailFragment, ForecastDetailFragment.TAG)
    }
}