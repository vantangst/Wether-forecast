package com.example.weatherforecast.ui.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherforecast.R
import com.example.weatherforecast.data.model.Forecast
import com.example.weatherforecast.extension.afterTextChanged
import com.example.weatherforecast.ui.adapter.ForecastAdapter
import com.example.weatherforecast.ui.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import org.jetbrains.annotations.TestOnly

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    private lateinit var edtSearch: EditText
    private lateinit var btnSearch: Button
    private lateinit var rvForecast: RecyclerView
    private lateinit var forecastAdapter: ForecastAdapter
    private lateinit var loading: ProgressBar
    private lateinit var tvEmpty: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        initView()
        observeData()
    }

    private fun initView() {
        loading = findViewById(R.id.pbLoading)
        edtSearch = findViewById(R.id.edtSearch)
        btnSearch = findViewById(R.id.btnSearch)
        rvForecast = findViewById(R.id.rvForecast)
        tvEmpty = findViewById(R.id.tvEmpty)
        rvForecast.layoutManager = LinearLayoutManager(this)
        rvForecast.setHasFixedSize(true)
        rvForecast.addItemDecoration(
            DividerItemDecoration(
                this,
                DividerItemDecoration.VERTICAL,
            )
        )
        forecastAdapter = ForecastAdapter()
        rvForecast.adapter = forecastAdapter
        btnSearch.setOnClickListener {
            fetchForecast(edtSearch.text.toString())
        }
        edtSearch.afterTextChanged { viewModel.handleSearchChange(it) }
    }

    private fun fetchForecast(keyword: String) {
        viewModel.searchForecast(
            keyword = keyword,
            onLoading = {
                processLoading(it)
            },
            onCompleted = {
                displayResult(it)
            },
            onError = {
                processError(it ?: getString(R.string.common_error))
            },
        )
    }

    private fun displayResult(result: List<Forecast>) {
        lifecycleScope.launchWhenResumed {
            forecastAdapter.setData(result)
            tvEmpty.visibility = View.GONE
        }
    }

    private fun processError(errorMsg: String) {
        lifecycleScope.launch {
            forecastAdapter.setData(emptyList())
            tvEmpty.visibility = View.VISIBLE
            tvEmpty.text = errorMsg
        }
    }

    private fun processLoading(isLoading: Boolean) {
        lifecycleScope.launch {
            loading.visibility = if (isLoading) View.VISIBLE else View.GONE
            edtSearch.isEnabled = !isLoading
            btnSearch.isClickable = !isLoading
        }
    }

    private fun observeData() {
        viewModel.isEnableSearchButton.observe(this) {
            btnSearch.isEnabled = it
        }
    }

    @TestOnly
    fun setTestViewModel(testViewModel: MainViewModel) {
        viewModel = testViewModel
    }
}