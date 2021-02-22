package com.itis.ganiev.baseproject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class WeatherHolder(
    itemView: View,
    private val itemClick: (String) -> Unit
) : RecyclerView.ViewHolder(itemView) {

    private val city: TextView = itemView.findViewById(R.id.tv_city)
    private val temperature: TextView = itemView.findViewById(R.id.tv_temperature)

    fun bind(weatherInfo: WeatherListResponse.WeatherInfo) {
        city.text = weatherInfo.name
        val wTemperature = weatherInfo.main.temp.toInt()
        temperature.text = itemView.resources.getString(R.string.celsius, wTemperature.toString())
        when (wTemperature) {
            in 20..1000 -> temperature.setTextColor(ContextCompat.getColor(itemView.context, android.R.color.holo_red_dark))
            in 10..20 -> temperature.setTextColor(ContextCompat.getColor(itemView.context, android.R.color.holo_red_light))
            in 0..10 -> temperature.setTextColor(ContextCompat.getColor(itemView.context, android.R.color.holo_orange_dark))
            in -10..0 -> temperature.setTextColor(ContextCompat.getColor(itemView.context, android.R.color.holo_blue_bright))
            in -20..-10 -> temperature.setTextColor(ContextCompat.getColor(itemView.context, android.R.color.holo_blue_light))
            in -273..-10 -> temperature.setTextColor(ContextCompat.getColor(itemView.context, android.R.color.holo_blue_dark))
        }
        itemView.setOnClickListener {
            itemClick(weatherInfo.name)
        }
    }

    companion object {
        fun create(parent: ViewGroup, itemClick: (String) -> Unit): WeatherHolder =
            WeatherHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.city_list_item, parent, false),
                itemClick
            )
    }

}
