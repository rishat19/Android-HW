package com.itis.ganiev.baseproject

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class WeatherAdapter(
    private var weatherListResponse: WeatherListResponse,
    private val itemClick: (String) -> Unit
)  : RecyclerView.Adapter<WeatherHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherHolder =
        WeatherHolder.create(parent, itemClick)

    override fun onBindViewHolder(holder: WeatherHolder, position: Int) {
        holder.bind(weatherListResponse.list[position])
    }

    override fun getItemCount(): Int = weatherListResponse.list.size

}