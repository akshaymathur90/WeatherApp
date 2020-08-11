package com.akshay.weatherapp.ui.weeklyforecast

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.akshay.weatherapp.R
import com.akshay.weatherapp.WeatherApplication
import com.akshay.weatherapp.database.DailyForecast
import com.akshay.weatherapp.models.Errors
import com.akshay.weatherapp.models.Location
import com.akshay.weatherapp.ui.MainActivity
import com.akshay.weatherapp.utils.ItemClickSupport
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_weekly_forecast.*
import javax.inject.Inject

class WeeklyForecastFragment : Fragment() {

    companion object {
        const val TAG = "WeeklyForecastFragment"
        fun newInstance(): WeeklyForecastFragment {
            return WeeklyForecastFragment()
        }
    }

    interface ForecastItemClickListener {
        fun onItemClick(forecast: DailyForecast)
    }

    @Inject
    lateinit var weeklyForecastViewModelFactory: WeeklyForecastViewModel.Factory

    private var listener: ForecastItemClickListener? = null
    private lateinit var adapter: WeeklyForecastAdapter
    private lateinit var viewModel: WeeklyForecastViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (context.applicationContext as WeatherApplication).appComponent.inject(this)
        if (context is MainActivity) {
            listener = context
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_weekly_forecast, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(
            this,
            weeklyForecastViewModelFactory
        ).get(WeeklyForecastViewModel::class.java)
        init()
    }

    private fun init() {
        adapter = WeeklyForecastAdapter()
        forecast_list.layoutManager = LinearLayoutManager(activity)
        forecast_list.adapter = adapter

        val itemDecoration: RecyclerView.ItemDecoration =
            DividerItemDecoration(activity, DividerItemDecoration.VERTICAL)
        forecast_list.addItemDecoration(itemDecoration)

        ItemClickSupport.addTo(forecast_list)
            .setOnItemClickListener { _, position, _ -> listener?.onItemClick(adapter.forecasts[position]) }

        viewModel.dailyForecasts.observe(viewLifecycleOwner, Observer { forecasts ->
            adapter.forecasts = forecasts
            adapter.notifyDataSetChanged()
        })

        viewModel.eventNetworkError.observe(viewLifecycleOwner, Observer { error ->
            error?.let {
                handleErrors(it)
            }
        })

        //TODO: use Location Services to pull current location
        viewModel.refreshForecasts(Location(37.335205, -121.881222))
    }

    private fun showNetworkError() {
        val isErrorShown = viewModel.isNetworkErrorShown.value?:true
        if (isErrorShown) {
            showErrorMsg(getString(R.string.error_network))
            viewModel.onNetworkErrorShown()
        }
    }

    private fun handleErrors(error: Errors) {
        when(error) {
            Errors.NETWORK_ERROR -> showNetworkError()
            Errors.UNKNOWN_ERROR -> showGenericError()
        }
    }

    private fun showGenericError() {
        val isErrorShown = viewModel.isNetworkErrorShown.value?:true
        if (isErrorShown) {
            showErrorMsg(getString(R.string.error_something_went_wrong))
            viewModel.onNetworkErrorShown()
        }
    }

    private fun showErrorMsg(msg: String) {
        Snackbar.make(forecast_list, msg, Snackbar.LENGTH_LONG)
            .show()
    }

}