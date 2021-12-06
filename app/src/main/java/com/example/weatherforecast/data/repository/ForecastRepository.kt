package com.example.weatherforecast.data.repository

import com.example.weatherforecast.data.api.ApiService
import com.example.weatherforecast.data.model.ForecastModel
import com.example.weatherforecast.data.model.api.ApiCode
import com.example.weatherforecast.data.model.api.ApiResponse
import com.example.weatherforecast.data.model.api.ApiError
import java.net.ConnectException

class ForecastRepository(private val apiService: ApiService) {
    suspend fun getDailyForecast(
        keyword: String,
        forecastDays: Int = 7,
        appId: String,
    ): ApiResponse<ForecastModel?, ApiError?> {
        val apiResponse = ApiResponse<ForecastModel?, ApiError?>()
        return try {
            val result = apiService.getDailyForecast(keyword, forecastDays, appId)
            apiResponse.code(result.code())
            if (result.isSuccessful) {
                apiResponse.result(result.body())
            } else {
                //todo get error body
                apiResponse.message(result.message())
                apiResponse.error(ApiError(result.code(), result.message()))
            }
        } catch (e: ConnectException) {
            apiResponse.error(ApiError(ApiCode.LOST_CONNECTION.value, "Connection exception"))
        } catch (e: Exception) {
            apiResponse.error(ApiError(ApiCode.UNKNOWN.value, "unknown exception"))
        }
    }
}