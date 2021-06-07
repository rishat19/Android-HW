package com.itis.ganiev.baseproject.presentation.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.itis.ganiev.baseproject.R
import com.itis.ganiev.baseproject.data.api.ApiFactory
import com.itis.ganiev.baseproject.data.api.response.WeatherResponse
import com.itis.ganiev.baseproject.data.database.entities.WeatherEntity
import com.itis.ganiev.baseproject.domain.FindCityUseCase
import com.itis.ganiev.baseproject.presentation.presenters.InfoPresenter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_info.*
import kotlinx.coroutines.launch
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import javax.inject.Inject

@AndroidEntryPoint
class InfoFragment : MvpAppCompatFragment(), InfoView {

    @Inject
    lateinit var useCase: FindCityUseCase

    @InjectPresenter
    lateinit var presenter: InfoPresenter

    @ProvidePresenter
    fun providePresenter() = InfoPresenter(useCase)

    private val ARG_PARAM1 = "cityName"
    private var cityName: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        arguments?.let {
            cityName = it.getString(ARG_PARAM1)
        }
        cityName?.let { presenter.onLoaded(it) }
        return inflater.inflate(R.layout.fragment_info, container, false)
    }

    override fun showWeather(weather: WeatherEntity) {
        initInfo(weather)
    }

    private fun initInfo(weather: WeatherEntity) {
        tv_info_city.text = weather.name
        tv_info_temperature.text =
            context?.resources?.getString(R.string.celsius, weather.main.temp.toInt().toString())
        tv_info_description.text = weather.innerWeather.description
        tv_info_wind.text = when (weather.wind.deg) {
            in 0..22 -> "Северный"
            in 23..67 -> "Северо-восточный"
            in 68..112 -> "Восточный"
            in 113..157 -> "Юго-восточный"
            in 158..202 -> "Южный"
            in 203..247 -> "Юго-западный"
            in 248..292 -> "Западный"
            in 293..337 -> "Северо-западный"
            in 338..360 -> "Северный"
            else -> "Непредсказуемый"
        }
        tv_info_wind_speed.text =
            context?.resources?.getString(R.string.speed, weather.wind.speed.toString())
        tv_info_humidity.text = weather.main.humidity.toString() + "%"
        tv_info_feel.text = context?.resources?.getString(
            R.string.celsius,
            weather.main.feelsLike.toInt().toString()
        )
    }

    companion object {
        fun newInstance(cityName: String) =
            InfoFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, cityName)
                }
            }
    }

}
