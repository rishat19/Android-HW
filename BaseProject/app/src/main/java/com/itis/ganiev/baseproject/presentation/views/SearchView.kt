package com.itis.ganiev.baseproject.presentation.views

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

@AddToEndSingle
interface SearchView : MvpView {

    fun showCityList(latitude: Double, longitude: Double)

    fun checkLocationPermission()

    fun requestPermissions()

}
