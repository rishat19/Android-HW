package com.itis.ganiev.baseproject.domain

import com.itis.ganiev.baseproject.data.database.entities.WeatherEntity
import com.itis.ganiev.baseproject.data.database.entities.City
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Named
import kotlin.coroutines.CoroutineContext

class FindCityUseCase @Inject constructor(
    private val weatherRepository: WeatherRepository,
    @Named("ioCoroutineContext")
    private val context: CoroutineContext
) {
    suspend fun getWeatherByCityName(cityName: String): WeatherEntity? =
        withContext(context) {
            weatherRepository.getWeatherByCityName(cityName)
        }

    suspend fun getWeatherByCoordinates(
        latitude: Double,
        longitude: Double,
        count: Int
    ): List<City> =
        withContext(context) {
            weatherRepository.getWeatherByCoordinates(latitude, longitude, count)
        }
}
