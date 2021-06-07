package com.itis.ganiev.baseproject.presentation.di

import android.content.Context
import com.google.android.gms.location.FusedLocationProviderClient
import com.itis.ganiev.baseproject.data.api.ApiFactory
import com.itis.ganiev.baseproject.data.api.WeatherApi
import com.itis.ganiev.baseproject.data.database.WeatherDAO
import com.itis.ganiev.baseproject.data.database.WeatherDatabase
import com.itis.ganiev.baseproject.data.repositories.LocationRepositoryImpl
import com.itis.ganiev.baseproject.data.repositories.WeatherRepositoryImpl
import com.itis.ganiev.baseproject.domain.LocationRepository
import com.itis.ganiev.baseproject.domain.WeatherRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import javax.inject.Named
import javax.inject.Singleton
import kotlin.coroutines.CoroutineContext

@Module
@InstallIn(SingletonComponent::class)
class WeatherAppModule {

    @Singleton
    @Provides
    @Named("weatherApi")
    fun provideWeatherApi(): WeatherApi = ApiFactory.weatherApi

    @Singleton
    @Provides
    @Named("weatherDao")
    fun provideWeatherDao(@ApplicationContext context: Context): WeatherDAO =
        WeatherDatabase.getInstance(context).weatherDAO

    @Singleton
    @Provides
    @Named("ioCoroutineContext")
    fun provideCoroutineContext(): CoroutineContext = Dispatchers.IO

    @Singleton
    @Provides
    fun provideFusedLocationProviderClient(
        @ApplicationContext context: Context,
    ): FusedLocationProviderClient = FusedLocationProviderClient(context)
}

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule{

    @Singleton
    @Binds
    abstract fun provideWeatherRepository(
        weatherRepositoryImpl: WeatherRepositoryImpl
    ) : WeatherRepository

    @Singleton
    @Binds
    abstract fun provideLocationRepository(
        locationRepositoryImpl: LocationRepositoryImpl
    ): LocationRepository
}
