package com.akshay.weatherapp.ui.weeklyforecast

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.akshay.weatherapp.R
import com.akshay.weatherapp.database.DailyForecast
import com.akshay.weatherapp.utils.Utils

class WeeklyForecastAdapter : RecyclerView.Adapter<WeeklyForecastAdapter.ViewHolder>() {

    var forecasts: List<DailyForecast> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.view_daily_forecast_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = forecasts.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val vm = forecasts[position]
        holder.bind(vm)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvDate: TextView = itemView.findViewById(R.id.tv_forecast_day)
        private val tvMin: TextView = itemView.findViewById(R.id.tv_min_temp)
        private val tvMax: TextView = itemView.findViewById(R.id.tv_max_temp)

        fun bind(forecast: DailyForecast) {
            val localDateTime = Utils.convertToLocalTime(forecast.timestamp, forecast.timezone)
            tvDate.text = Utils.formatDateTime(localDateTime)
            tvMin.text = forecast.temp_min.toString()
            tvMax.text = forecast.temp_max.toString()
        }
    }
}