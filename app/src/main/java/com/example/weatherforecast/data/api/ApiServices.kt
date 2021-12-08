package com.example.weatherforecast.data.api

import com.example.weatherforecast.BuildConfig
import com.example.weatherforecast.data.model.ForecastModel
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET(ApiEndpoint.DAILY_FORECAST)
    suspend fun getDailyForecast(
        @Query("q") keyword: String,
        @Query("cnt") forecastDays: Int,
        @Query("appid") appId: String,
        @Query("units") unitsType: String,
    ): Response<ForecastModel?>
}

object ApiServiceClient {
    fun build(client: OkHttpClient): ApiService {
        val builder = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
        val retrofit = builder.client(client).build()
        return retrofit.create(ApiService::class.java)
    }
}