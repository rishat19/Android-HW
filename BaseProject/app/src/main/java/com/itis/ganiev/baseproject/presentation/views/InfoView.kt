package com.itis.ganiev.baseproject.presentation.views

import com.itis.ganiev.baseproject.data.database.entities.WeatherEntity
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

@AddToEndSingle
interface InfoView : MvpView {
    fun showWeather(weather: WeatherEntity)
}
