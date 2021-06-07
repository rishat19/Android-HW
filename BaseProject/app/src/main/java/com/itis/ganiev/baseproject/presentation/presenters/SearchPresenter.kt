package com.itis.ganiev.baseproject.presentation.presenters

import android.content.Context
import com.itis.ganiev.baseproject.data.database.entities.City
import com.itis.ganiev.baseproject.domain.FindCityUseCase
import com.itis.ganiev.baseproject.domain.LocationUseCase
import com.itis.ganiev.baseproject.presentation.views.SearchView
import kotlinx.coroutines.launch
import moxy.MvpPresenter
import moxy.presenterScope

class SearchPresenter(
    private val findCityUseCase: FindCityUseCase,
    private val locationUseCase: LocationUseCase,
    private val context: Context
) : MvpPresenter<SearchView>() {

    private val DEFAULT_LATITUDE = 55.7887
    private val DEFAULT_LONGITUDE = 49.1221

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        checkLocationPermission()
    }

    fun checkLocationPermission() {
        viewState.checkLocationPermission()
    }

    fun onCheckPermissionResult(result: Boolean) {
        if (result) {
            presenterScope.launch {
                try {
                    locationUseCase.getUserLocation().let {
                        showCities(it.latitude, it.longitude)
                    }
                } catch (e: Throwable) {
                    showDefaultCities()
                }

            }
        } else {
            viewState.requestPermissions()
        }
    }

    suspend fun getCityList(latitude: Double, longitude: Double) : List<City> {
        return findCityUseCase.getWeatherByCoordinates(latitude, longitude, 20)
    }

    private fun showCities(latitude: Double, longitude: Double) {
        presenterScope.launch {
            viewState.showCityList(latitude, longitude)
        }
    }

    fun showDefaultCities() {
        presenterScope.launch {
            viewState.showCityList(DEFAULT_LATITUDE, DEFAULT_LONGITUDE)
        }
    }

}
