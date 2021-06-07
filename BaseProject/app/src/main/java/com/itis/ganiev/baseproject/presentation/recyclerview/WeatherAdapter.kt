package com.itis.ganiev.baseproject.presentation.recyclerview

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.itis.ganiev.baseproject.data.database.entities.City

class WeatherAdapter(
    private var cityList: List<City>,
    private val itemClick: (String) -> Unit
) : RecyclerView.Adapter<WeatherHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherHolder =
        WeatherHolder.create(parent, itemClick)

    override fun onBindViewHolder(holder: WeatherHolder, position: Int) {
        holder.bind(cityList[position])
    }

    override fun getItemCount(): Int = cityList.size

}
