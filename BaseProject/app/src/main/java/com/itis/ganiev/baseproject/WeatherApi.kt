package com.itis.ganiev.baseproject

import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("weather?units=metric&lang=ru")
    suspend fun getWeather(
        @Query("q") cityName: String
    ) : WeatherResponse

    @GET("find?units=metric&lang=ru")
    suspend fun getWeatherList(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("cnt") count: Int
    ) : WeatherListResponse

}
