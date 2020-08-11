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
import com.akshay.weatherapp.models.Location
import kotlinx.android.synthetic.main.fragment_weekly_forecast.*
import javax.inject.Inject

class WeeklyForecastFragment : Fragment() {

    companion object {
        const val TAG = "WeeklyForecastFragment"
        fun newInstance(): WeeklyForecastFragment {
            return WeeklyForecastFragment()
        }
    }

    @Inject
    lateinit var weeklyForecastViewModelFactory: WeeklyForecastViewModel.Factory

    private lateinit var adapter: WeeklyForecastAdapter
    private lateinit var viewModel: WeeklyForecastViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (context.applicationContext as WeatherApplication).appComponent.inject(this)
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
        val adapter = WeeklyForecastAdapter()
        forecast_list.layoutManager = LinearLayoutManager(activity)
        forecast_list.adapter = adapter

        val itemDecoration: RecyclerView.ItemDecoration =
            DividerItemDecoration(activity, DividerItemDecoration.VERTICAL)
        forecast_list.addItemDecoration(itemDecoration)

        viewModel.dailyForecasts.observe(viewLifecycleOwner, Observer { forecasts ->
            adapter.forecasts = forecasts
            adapter.notifyDataSetChanged()
        })

        viewModel.refreshForecasts(Location(37.335205, -121.881222))
    }


}