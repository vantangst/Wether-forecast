package com.example.weatherforecast.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherforecast.R
import com.example.weatherforecast.data.model.Forecast
import com.example.weatherforecast.data.model.TemperatureUnitLabel
import com.example.weatherforecast.extension.toDateString
import kotlin.math.roundToInt

class ForecastAdapter :
    RecyclerView.Adapter<ForecastAdapter.ViewHolder>() {
    private val dataSet = mutableListOf<Forecast>()

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val tvDate: TextView
        private val tvAvgTemperature: TextView
        private val tvPressure: TextView
        private val tvHumidity: TextView
        private val tvDescription: TextView
        private val context: Context

        init {
            tvDate = view.findViewById(R.id.tvDate)
            tvAvgTemperature = view.findViewById(R.id.tvAvgTemperature)
            tvPressure = view.findViewById(R.id.tvPressure)
            tvHumidity = view.findViewById(R.id.tvHumidity)
            tvDescription = view.findViewById(R.id.tvDescription)
            context = view.context
        }


        fun bind(item: Forecast) {
            tvDate.text = context.getString(
                R.string.label_date_with_value,
                item.date?.toDateString(),
            )
            tvAvgTemperature.text = context.getString(
                R.string.label_avg_temperature_with_value,
                "${item.temp?.eve?.roundToInt()}${TemperatureUnitLabel.C.value}",
            )
            tvPressure.text = context.getString(
                R.string.label_pressure_with_value,
                item.pressure ?: 0,
            )
            tvHumidity.text = context.getString(
                R.string.label_humidity_with_value,
                 "${item.humidity ?: 0}%",
            )
            tvDescription.text = context.getString(
                R.string.label_description_with_value,
                item.weather?.firstOrNull()?.description ?: "",
            )
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_forecast, viewGroup, false)
        return ViewHolder(view)
    }


    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(dataSet[position])
    }

    override fun getItemCount() = dataSet.size

    @SuppressLint("NotifyDataSetChanged")
    fun setData(dataList: List<Forecast>) {
        dataSet.clear()
        dataSet.addAll(dataList)
        notifyDataSetChanged()
    }

}