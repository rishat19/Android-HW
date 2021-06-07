package com.itis.ganiev.baseproject.data.database.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "city")
data class CityEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    @Embedded(prefix = "city_")
    var city: City
)
