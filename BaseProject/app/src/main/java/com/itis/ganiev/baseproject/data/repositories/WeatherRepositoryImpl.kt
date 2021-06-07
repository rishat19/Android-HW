package com.itis.ganiev.baseproject.data.repositories

import com.itis.ganiev.baseproject.data.api.WeatherApi
import com.itis.ganiev.baseproject.data.database.WeatherDAO
import com.itis.ganiev.baseproject.data.database.entities.CityEntity
import com.itis.ganiev.baseproject.data.database.entities.WeatherEntity
import com.itis.ganiev.baseproject.domain.WeatherRepository
import com.itis.ganiev.baseproject.data.database.entities.City
import javax.inject.Inject
import javax.inject.Named

class WeatherRepositoryImpl @Inject constructor(
    @Named("weatherApi")
    private val weatherApi: WeatherApi,
    @Named("weatherDao")
    private val weatherDAO: WeatherDAO
) : WeatherRepository {
    override suspend fun getWeatherByCityName(cityName: String): WeatherEntity? {
        return try {
            val weatherResponse = weatherApi.getWeather(cityName)
            WeatherEntity(
                weatherResponse.id,
                weatherResponse.name,
                weatherResponse.main,
                weatherResponse.weather[0],
                weatherResponse.wind
            ).also {
                weatherDAO.insertWeather(it)
            }
        } catch (e: Throwable) {
            weatherDAO.getWeatherByName(cityName).firstOrNull()
        }
    }

    override suspend fun getWeatherByCoordinates(
        latitude: Double,
        longitude: Double,
        count: Int
    ): List<City> {
        return try {
            weatherApi.getWeatherList(latitude, longitude, count).list.onEach {
                weatherDAO.insertWeather(
                    WeatherEntity(
                        it.id,
                        it.name,
                        it.main,
                        it.weather[0],
                        it.wind
                    )
                )
                weatherDAO.insertNearestCity(
                    CityEntity(
                        city = City(
                            it.name,
                            it.main.temp
                        )
                    )
                )
            }.map { weatherResponse ->
                City(
                    weatherResponse.name,
                    weatherResponse.main.temp
                )
            }
        } catch (e: Throwable) {
            weatherDAO.getNearestCities().map {
                it.city
            }
        }
    }
}
