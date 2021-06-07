package com.itis.ganiev.baseproject.domain

import android.location.Location

interface LocationRepository {
    suspend fun getUserLocation(): Location
}
