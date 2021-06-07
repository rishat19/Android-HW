package com.itis.ganiev.baseproject.data.api.response

import com.google.gson.annotations.SerializedName

data class WeatherListResponse(
    @SerializedName("cod")
    val cod: String,
    @SerializedName("count")
    val count: Int,
    @SerializedName("list")
    val list: List<WeatherResponse>,
    @SerializedName("message")
    val message: String
)
