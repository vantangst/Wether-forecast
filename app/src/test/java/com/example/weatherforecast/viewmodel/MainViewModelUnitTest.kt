package com.example.weatherforecast.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.weatherforecast.BuildConfig
import com.example.weatherforecast.data.model.Forecast
import com.example.weatherforecast.data.model.ForecastModel
import com.example.weatherforecast.data.model.TemperatureType
import com.example.weatherforecast.data.model.api.ApiCode
import com.example.weatherforecast.data.model.api.ApiError
import com.example.weatherforecast.data.model.api.ApiResponse
import com.example.weatherforecast.data.repository.ForecastRepository
import com.example.weatherforecast.ui.viewmodel.MainViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Test

import org.junit.Before
import org.junit.Rule
import org.mockito.Mockito

/**
 * MainViewModel local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

class MainViewModelUnitTest {
    private lateinit var mainViewModel: MainViewModel
    private lateinit var forecastRepository: ForecastRepository
    private lateinit var isEnableSearchButtonObserver: Observer<Boolean>
    private val keywordValid = "saigon"
    private val keywordInvalid = "sa"
    private val appId = BuildConfig.WEATHER_APP_ID
    private val unitsType = TemperatureType.C.value
    private val successCode = ApiCode.SUCCESS.value
    private val failCode = ApiCode.FAILURE.value
    private val errorMessage = "Error message"

    /**
    //https://jeroenmols.com/blog/2019/01/17/livedatajunit5/
    //Method getMainLooper in android.os.Looper not mocked
    */
    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        forecastRepository = Mockito.mock(
            ForecastRepository::class.java
        )
        mainViewModel = MainViewModel(forecastRepository)
        isEnableSearchButtonObserver = Mockito.mock(Observer::class.java) as Observer<Boolean>
    }

    @Test
    @ExperimentalCoroutinesApi
    fun `should be notified after fetched wheather data`() = runBlockingTest {
        // Arrange
        val forecastModel = ForecastModel(list = listOf(Forecast()))
        val apiResponse = ApiResponse<ForecastModel?, ApiError?>()
        apiResponse.code(successCode)
        apiResponse.result(forecastModel)

        val function: (List<Forecast>) -> Unit = {}
        val callback = Mockito.mock(function::class.java)

        Mockito.`when`(forecastRepository.getDailyForecast(keyword = keywordValid, appId = appId, unitsType = unitsType)).thenReturn(apiResponse)

        // Act
        mainViewModel.searchForecast(
            keyword = keywordValid,
            onLoading = {},
            onCompleted = callback,
            onError = {}
        )

        // Assert
        Mockito.verify(callback, Mockito.times(1)).invoke(forecastModel.list)
    }

    @Test
    @ExperimentalCoroutinesApi
    fun `should return ApiError when call getDailyForecast() fail`() = runBlockingTest {
        // Arrange
        val apiResponse = ApiResponse<ForecastModel?, ApiError?>()
        apiResponse.code(failCode)
        apiResponse.error(ApiError(failCode, errorMessage))

        val function: (String?) -> Unit = {}
        val callback = Mockito.mock(function::class.java)

        Mockito.`when`(forecastRepository.getDailyForecast(keyword = keywordValid, appId = appId, unitsType = unitsType)).thenReturn(apiResponse)

        // Act
        mainViewModel.searchForecast(
            keyword = keywordValid,
            onLoading = {},
            onCompleted = {},
            onError = callback
        )

        // Assert
        Mockito.verify(callback, Mockito.times(1)).invoke(errorMessage)
    }

    @Test
    @ExperimentalCoroutinesApi
    fun `should return false when call handleSearchChange() with an invalid keyword`() = runBlockingTest {
        // Arrange

        // Act
        with(mainViewModel) {
            mainViewModel.handleSearchChange(keywordInvalid)
            isEnableSearchButton.observeForever(isEnableSearchButtonObserver)
        }

        // Assert
        Assert.assertEquals(false, mainViewModel.isEnableSearchButton.value)
    }

    @Test
    @ExperimentalCoroutinesApi
    fun `should return true when call handleSearchChange() with a valid keyword`() = runBlockingTest {
        // Arrange

        // Act
        with(mainViewModel) {
            mainViewModel.handleSearchChange(keywordValid)
            isEnableSearchButton.observeForever(isEnableSearchButtonObserver)
        }

        // Assert
        Assert.assertEquals(true, mainViewModel.isEnableSearchButton.value)
    }
}