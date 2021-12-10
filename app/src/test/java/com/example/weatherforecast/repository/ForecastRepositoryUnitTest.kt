package com.example.weatherforecast.repository

import com.example.weatherforecast.data.api.ApiService
import com.example.weatherforecast.data.model.ForecastModel
import com.example.weatherforecast.data.model.api.ApiCode
import com.example.weatherforecast.data.repository.ForecastRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import okhttp3.ResponseBody
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import retrofit2.Response


class ForecastRepositoryUnitTest {

    private lateinit var apiService: ApiService
    private lateinit var forecastRepository: ForecastRepository
    private val keyword = "saigon"
    private val appId = "61c6fbec4b93ac653c492ba806fc345d"
    private val unitsType = "Metric"
    private val forecastDays = 5

    @Before
    fun setup() {
        apiService = Mockito.mock(
            ApiService::class.java
        )
        forecastRepository = ForecastRepository(apiService)
    }

    @Test
    @ExperimentalCoroutinesApi
    fun `should return forecast data when call getDailyForecast() success`() = runBlockingTest {
        // Arrange
        val forecastModel = Mockito.mock(
            ForecastModel::class.java
        )
        val responseFromService = Response.success(forecastModel)

        Mockito.`when`(
            apiService.getDailyForecast(keyword, forecastDays, appId, unitsType)
        ).thenReturn(responseFromService)

        // Act
        val result = forecastRepository.getDailyForecast(keyword, forecastDays, appId, unitsType)

        // Assert
        Assert.assertEquals(forecastModel, result.result())
    }

    @Test
    @ExperimentalCoroutinesApi
    fun `should return empty data when backend error`() = runBlockingTest {
        // Arrange
        val responseBody = Mockito.mock(
            ResponseBody::class.java
        )
        val responseFromService = Response.error<ForecastModel>(500, responseBody)

        Mockito.`when`(
            apiService.getDailyForecast(keyword, forecastDays, appId, unitsType)
        ).thenReturn(responseFromService)

        // Act
        val result = forecastRepository.getDailyForecast(keyword, forecastDays, appId, unitsType)

        // Assert
        Assert.assertEquals(null, result.result())
    }

    @Test
    @ExperimentalCoroutinesApi
    fun `should return empty data when some unkown error happened`() = runBlockingTest {

        Mockito.`when`(
            apiService.getDailyForecast(keyword, forecastDays, appId, unitsType)
        ).thenThrow(NullPointerException::class.java)

        // Act
        val result = forecastRepository.getDailyForecast(keyword, forecastDays, appId, unitsType)

        // Assert
        Assert.assertEquals(ApiCode.UNKNOWN.value, result.error()?.code ?: 0)
    }

   // todo add 1 more test function for case: UnknownHostException
}