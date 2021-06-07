package com.itis.ganiev.baseproject.presentation.presenters

import com.itis.ganiev.baseproject.domain.FindCityUseCase
import com.itis.ganiev.baseproject.presentation.views.InfoView
import kotlinx.coroutines.launch
import moxy.MvpPresenter
import moxy.presenterScope

class InfoPresenter(
    private val findCityUseCase: FindCityUseCase
) : MvpPresenter<InfoView>() {
    fun onLoaded(cityName: String) {
        presenterScope.launch {
            findCityUseCase.getWeatherByCityName(cityName)?.let {
                viewState.showWeather(it)
            }
        }
    }
}
