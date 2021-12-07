package com.example.weatherforecast.data.model

import com.google.gson.annotations.SerializedName

enum class TemperatureType (val value: String) {
    K("Default"),
    C("Metric"),
    F("Imperial"),
}

data class ForecastModel(
    @SerializedName("city") var city: City? = null,
    @SerializedName("cod") var cod: String? = null,
    @SerializedName("message") var message: String? = null,
    @SerializedName("cnt") var forecastDays: Int? = null,
    @SerializedName("list") var list: List<Forecast> = emptyList()
)

data class Coordinate(
    @SerializedName("lon") var lon: Double? = null,
    @SerializedName("lat") var lat: Double? = null
)

data class City(
    @SerializedName("id") var id: Int? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("coord") var coordinate: Coordinate? = null,
    @SerializedName("country") var country: String? = null,
    @SerializedName("population") var population: Int? = null,
    @SerializedName("timezone") var timezone: Int? = null

)

data class Temperature(
    @SerializedName("day") var day: Double? = null,
    @SerializedName("min") var min: Double? = null,
    @SerializedName("max") var max: Double? = null,
    @SerializedName("night") var night: Double? = null,
    @SerializedName("eve") var eve: Double? = null,
    @SerializedName("morn") var morn: Double? = null

)

data class FeelsLike(
    @SerializedName("day") var day: Double? = null,
    @SerializedName("night") var night: Double? = null,
    @SerializedName("eve") var eve: Double? = null,
    @SerializedName("morn") var morn: Double? = null

)

data class Weather(
    @SerializedName("id") var id: Int? = null,
    @SerializedName("main") var main: String? = null,
    @SerializedName("description") var description: String? = null,
    @SerializedName("icon") var icon: String? = null

)

data class Forecast(
    @SerializedName("dt") var dt: Int? = null,
    @SerializedName("sunrise") var sunrise: Int? = null,
    @SerializedName("sunset") var sunset: Int? = null,
    @SerializedName("temp") var temp: Temperature?,
    @SerializedName("feels_like") var feelsLike: FeelsLike? = null,
    @SerializedName("pressure") var pressure: Int? = null,
    @SerializedName("humidity") var humidity: Int? = null,
    @SerializedName("weather") var weather: List<Weather>? = null,
    @SerializedName("speed") var speed: Double? = null,
    @SerializedName("deg") var deg: Int? = null,
    @SerializedName("gust") var gust: Double? = null,
    @SerializedName("clouds") var clouds: Int? = null,
    @SerializedName("pop") var pop: Double? = null

)