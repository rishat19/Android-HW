package com.itis.ganiev.baseproject.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.itis.ganiev.baseproject.data.database.entities.CityEntity
import com.itis.ganiev.baseproject.data.database.entities.WeatherEntity

@Database(
    entities = [
        WeatherEntity::class,
        CityEntity::class
    ],
    version = 2,
    exportSchema = false
)
abstract class WeatherDatabase : RoomDatabase() {

    abstract val weatherDAO: WeatherDAO

    companion object {
        @Volatile
        private var INSTANCE: WeatherDatabase? = null

        val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("CREATE TABLE 'city' ('id' INTEGER PRIMARY KEY NOT NULL, 'city_name' TEXT NOT NULL, 'city_temperature' REAL NOT NULL)")
            }
        }

        fun getInstance(context: Context): WeatherDatabase {
            synchronized(this) {
                return INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    WeatherDatabase::class.java,
                    "weather_db"
                ).addMigrations(MIGRATION_1_2)
                    .build().also {
                        INSTANCE = it
                    }
            }
        }
    }

}
