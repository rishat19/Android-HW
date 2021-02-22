package com.itis.ganiev.baseproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import kotlinx.android.synthetic.main.fragment_info.*
import kotlinx.coroutines.launch

private const val ARG_PARAM1 = "cityName"

class InfoFragment : Fragment() {

    private var cityName: String? = null
    private val api = ApiFactory.weatherApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            cityName = it.getString(ARG_PARAM1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cityName?.let {
            lifecycleScope.launch {
                initInfo(api.getWeather(it))
            }
        }
    }

    private fun initInfo(weather: WeatherResponse) {
        tv_info_city.text = weather.name
        tv_info_temperature.text = context?.resources?.getString(R.string.celsius, weather.main.temp.toInt().toString())
        tv_info_description.text = weather.weather[0].description
        tv_info_wind.text = when(weather.wind.deg) {
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
        tv_info_wind_speed.text = context?.resources?.getString(R.string.speed, weather.wind.speed.toString())
        tv_info_humidity.text = weather.main.humidity.toString() + "%"
        tv_info_feel.text = context?.resources?.getString(R.string.celsius, weather.main.feelsLike.toInt().toString())
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
