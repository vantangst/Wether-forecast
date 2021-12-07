package com.example.weatherforecast.extension

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**
 * Convert timestamp in seconds to date string.
 *
 * input: timestamp in seconds
 *
 * return: date in string
 */
fun Long.toDateString(
    dateFormat: String = "EEE, dd MMM yyyy", timeZone: TimeZone = TimeZone.getTimeZone(
        "UTC"
    )
): String {
    return try {
        val date = Date(this * 1000)
        val parser = SimpleDateFormat(dateFormat, Locale.getDefault())
        parser.timeZone = timeZone
        parser.format(date)
    } catch (e: ParseException) {
        ""
    }
}