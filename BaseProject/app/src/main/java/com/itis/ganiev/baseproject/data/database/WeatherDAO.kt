package com.itis.ganiev.baseproject.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.itis.ganiev.baseproject.data.database.entities.CityEntity
import com.itis.ganiev.baseproject.data.database.entities.WeatherEntity

@Dao
interface WeatherDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWeather(weather: WeatherEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNearestCity(city: CityEntity)

    @Query("SELECT * FROM city ORDER BY id DESC LIMIT 20 ")
    suspend fun getNearestCities(): List<CityEntity>

    @Query("SELECT * FROM WeatherEntity WHERE id = :id")
    suspend fun getWeatherById(id: Int): List<WeatherEntity>

    @Query("SELECT * FROM WeatherEntity WHERE name = :cityName")
    suspend fun getWeatherByName(cityName: String): List<WeatherEntity>

}
