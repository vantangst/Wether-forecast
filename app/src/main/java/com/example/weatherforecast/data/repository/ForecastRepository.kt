package com.example.weatherforecast.data.repository

import com.example.weatherforecast.data.api.ApiService
import com.example.weatherforecast.data.model.ForecastModel
import com.example.weatherforecast.data.model.api.ApiCode
import com.example.weatherforecast.data.model.api.ApiResponse
import com.example.weatherforecast.data.model.api.ApiError
import java.net.UnknownHostException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ForecastRepository @Inject constructor(private val apiService: ApiService) {
    suspend fun getDailyForecast(
        keyword: String,
        forecastDays: Int = 7,
        appId: String,
        unitsType: String,
    ): ApiResponse<ForecastModel?, ApiError?> {
        val apiResponse = ApiResponse<ForecastModel?, ApiError?>()
        return try {
            val result = apiService.getDailyForecast(keyword, forecastDays, appId, unitsType)
            apiResponse.code(result.code())
            if (result.isSuccessful) {
                apiResponse.result(result.body())
            } else {
                //todo get error body
                apiResponse.message(result.message())
                apiResponse.error(ApiError(result.code(), result.message()))
            }
        } catch (e: UnknownHostException) {
            apiResponse.error(ApiError(ApiCode.LOST_CONNECTION.value, "Connection exception"))
        } catch (e: Exception) {
            apiResponse.error(ApiError(ApiCode.UNKNOWN.value, "unknown exception"))
        }
    }
}