package com.example.weatherforecast.ui.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherforecast.R
import com.example.weatherforecast.ui.adapter.ForecastAdapter
import com.example.weatherforecast.ui.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModels()
    private lateinit var edtSearch: EditText
    private lateinit var btnSearch: Button
    private lateinit var rvForecast: RecyclerView
    private lateinit var forecastAdapter: ForecastAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }

    private fun initView() {
        edtSearch = findViewById(R.id.edtSearch)
        btnSearch = findViewById(R.id.btnSearch)
        rvForecast = findViewById(R.id.rvForecast)
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
    }

    private fun fetchForecast(keyword: String) {
        viewModel.searchForecast(
            keyword = keyword,
            onLoading = {},
            onCompleted = {
                lifecycleScope.launchWhenResumed {
                    forecastAdapter.setData(it)
                }
            },
            onError = {},
        )
    }

    private fun observeData() {

    }
}