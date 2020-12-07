package com.itis.ganiev.baseproject

import androidx.annotation.DrawableRes
import androidx.annotation.RawRes

data class Track(
    val id: Int,
    val author: String,
    val title: String,
    @DrawableRes val cover: Int,
    @RawRes val sound: Int
)