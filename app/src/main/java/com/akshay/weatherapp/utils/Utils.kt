package com.akshay.weatherapp.utils

import org.joda.time.DateTime
import org.joda.time.DateTimeZone
import org.joda.time.format.DateTimeFormat
import java.time.format.DateTimeFormatter

class Utils {

    companion object {
        fun convertToLocalTime(timestamp: Long, timezone: String): DateTime {
            val dateTimeZone = DateTimeZone.forID(timezone)
            return DateTime(timestamp, dateTimeZone)
        }

        fun formatDateTime(dateTime: DateTime):String {
            val formatter = DateTimeFormat.forPattern("E dd MMM")
            return dateTime.toString(formatter)
        }
    }
}