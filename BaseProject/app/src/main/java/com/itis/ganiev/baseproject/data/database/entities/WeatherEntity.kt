package com.itis.ganiev.baseproject.data.database.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.itis.ganiev.baseproject.data.api.response.WeatherResponse

@Entity
data class WeatherEntity(
    @PrimaryKey
    var id: Int,
    var name: String,
    @Embedded(prefix = "main_")
    var main: WeatherResponse.Main,
    @Embedded(prefix = "weather_")
    var innerWeather: WeatherResponse.Weather,
    @Embedded(prefix = "wind_")
    var wind: WeatherResponse.Wind
)
