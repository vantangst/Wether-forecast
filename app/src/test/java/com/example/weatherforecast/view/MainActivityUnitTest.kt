package com.example.weatherforecast.view

import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherforecast.R
import com.example.weatherforecast.data.model.Forecast
import com.example.weatherforecast.ui.view.MainActivity
import com.example.weatherforecast.ui.viewmodel.MainViewModel
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.*
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule
import org.robolectric.RobolectricTestRunner
import org.robolectric.Robolectric
import org.robolectric.android.controller.ActivityController


@RunWith(RobolectricTestRunner::class)
class MainActivityUnitTest {

    private val forecastList = listOf(
        Forecast(date = 1639037296),
        Forecast(date = 1638037296),
        Forecast(date = 1637037296),
        Forecast(date = 1636037296),
        Forecast(date = 1635037296),
        Forecast(date = 1634037296),
        Forecast(date = 1633037296),
        Forecast(date = 1632037296),
        Forecast(date = 1631037296),
    )

    private lateinit var activity: MainActivity
    private lateinit var activityController: ActivityController<MainActivity>
    @Mock
    private lateinit var viewModel: MainViewModel
    @Mock
    private lateinit var isEnableSearchButtonLiveData: LiveData<Boolean>
    @Captor
    private lateinit var isEnableSearchButtonCaptor: ArgumentCaptor<Observer<Boolean>>

    @get:Rule
    val mockitoRule: MockitoRule = MockitoJUnit.rule()

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private val edtSearch: EditText
    get() = activity.findViewById(R.id.edtSearch)

    private val loading: ProgressBar
        get() = activity.findViewById(R.id.pbLoading)

    private val btnSearch: Button
        get() = activity.findViewById(R.id.btnSearch)

    private val tvEmpty: TextView
        get() = activity.findViewById(R.id.tvEmpty)

    private val rvForecast: RecyclerView
        get() = activity.findViewById(R.id.rvForecast)

    @Before
    fun setUp() {
        Mockito.`when`(viewModel.isEnableSearchButton).thenReturn(isEnableSearchButtonLiveData)
        activityController = Robolectric.buildActivity(MainActivity::class.java)
        activity = activityController.get()

        activityController.create()
        activity.setTestViewModel(viewModel)

        activityController.start().visible()
    }

    @Test
    fun `should disable Get Weather button on create()`() {
        assertEquals(false, btnSearch.isEnabled)
    }

    @Test
    fun `has hidden search progress bar on create()`() {
        assertEquals(View.GONE, loading.visibility)
    }

    @Test
    fun `should change text when input data`() {
        edtSearch.setText("aaa")
        btnSearch.performClick()
        assertEquals("aaa", edtSearch.text.toString())
    }
}