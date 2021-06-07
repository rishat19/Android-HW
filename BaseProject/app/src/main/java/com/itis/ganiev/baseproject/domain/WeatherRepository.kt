package com.itis.ganiev.baseproject.domain

import com.itis.ganiev.baseproject.data.database.entities.WeatherEntity
import com.itis.ganiev.baseproject.data.database.entities.City

interface WeatherRepository {
    suspend fun getWeatherByCityName(cityName: String) : WeatherEntity?
    suspend fun getWeatherByCoordinates(latitude: Double, longitude: Double, count: Int): List<City>
}
