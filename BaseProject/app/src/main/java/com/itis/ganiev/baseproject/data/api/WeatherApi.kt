package com.itis.ganiev.baseproject.data.api

import com.itis.ganiev.baseproject.data.api.response.WeatherListResponse
import com.itis.ganiev.baseproject.data.api.response.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("weather")
    suspend fun getWeather(
        @Query("q") cityName: String
    ): WeatherResponse

    @GET("find")
    suspend fun getWeatherList(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("cnt") count: Int
    ): WeatherListResponse

}
