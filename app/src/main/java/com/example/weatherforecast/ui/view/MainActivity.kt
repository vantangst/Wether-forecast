package com.example.weatherforecast.ui.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import androidx.activity.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherforecast.R
import com.example.weatherforecast.ui.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModels()
    private lateinit var edtSearch: EditText
    private lateinit var btnSearch: Button
    private lateinit var rvForecast: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }

    private fun initView() {
        edtSearch = findViewById(R.id.edtSearch)
        btnSearch = findViewById(R.id.btnSearch)
        rvForecast = findViewById(R.id.rvForecast)
        btnSearch.setOnClickListener {
            fetchForecast(edtSearch.text.toString())
        }
    }

    private fun fetchForecast(keyword: String) {
        viewModel.searchForecast(
            keyword = keyword,
            onLoading = {},
            onCompleted = {},
            onError = {},
        )
    }

    private fun observeData() {

    }
}