package com.example.weatherforecast.data.api

import com.example.weatherforecast.BuildConfig
import com.example.weatherforecast.data.model.ForecastModel
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit


interface ApiService {
    @GET(NetworkEndpoint.DAILY_FORECAST)
    suspend fun getDailyForecast(
        @Query("q") keyword: String,
        @Query("cnt") forecastDays: Int,
        @Query("appid") appId: String,
    ): Response<ForecastModel?>
}


object ApiServiceImpl {

    private val httpClient = OkHttpClient.Builder()

    private val builder = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())

    private var retrofit = builder.build()


    fun getApiService(): ApiService {
        httpClient.connectTimeout(30, TimeUnit.SECONDS)
        httpClient.readTimeout(30, TimeUnit.SECONDS)
        httpClient.writeTimeout(30, TimeUnit.SECONDS)
        builder.client(httpClient.build())
        retrofit = builder.build()
        return retrofit.create(ApiService::class.java)
    }
}