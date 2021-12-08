package com.example.weatherforecast.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherforecast.BuildConfig
import com.example.weatherforecast.data.model.Forecast
import com.example.weatherforecast.data.model.TemperatureType
import com.example.weatherforecast.data.repository.ForecastRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: ForecastRepository
) : ViewModel() {

    private val _isEnableSearchButton = MutableLiveData(false)
    val isEnableSearchButton: LiveData<Boolean> = _isEnableSearchButton

    fun searchForecast(
        keyword: String,
        onLoading: (Boolean) -> Unit,
        onError: (String?) -> Unit,
        onCompleted: (List<Forecast>) -> Unit,
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            onLoading(true)
            val result = repository.getDailyForecast(
                keyword = keyword,
                appId = BuildConfig.WEATHER_APP_ID,
                unitsType = TemperatureType.C.value,
            )
            onLoading(false)
            if (result.isSuccess()) {
                result.result()?.let {
                    onCompleted(it.list)
                }
            } else {
                onError(result.error()?.message)
            }
        }
    }

    fun handleSearchChange(keyword: String) {
        _isEnableSearchButton.postValue(validateSearchInput(keyword))
    }

    private fun validateSearchInput(keyword: String): Boolean {
        return (keyword.length >= 3)
    }

}