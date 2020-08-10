package com.akshay.weatherapp.network

import com.akshay.weatherapp.models.Location

class NetworkUtils {
    companion object {
        private const val appId = "appid"
        private const val latitude = "lat"
        private const val longitude = "lon"
        private const val exclude = "exclude"
        private const val excludedData = "minutely,hourly"
        private const val units = "units"
        private const val metric = "metric"
        private const val appKey = "aaf3b45b1177933f4f78bc631caf557f"

        fun getParams(location: Location): Map<String, String> {
            return mapOf(
                latitude to location.lat.toString(),
                longitude to location.lng.toString(),
                exclude to excludedData,
                units to metric,
                appId to appKey
            )
        }
    }
}