package com.itis.ganiev.baseproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_country_info.*
import kotlinx.android.synthetic.main.activity_country_info.iv_flag
import kotlinx.android.synthetic.main.activity_country_info.tv_country_name
import kotlinx.android.synthetic.main.item_country.*

class CountryInfoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_country_info)

        val id = intent.extras?.getInt("ID", -1) ?: 0
        val country: Country? = CountryRepository.getCountries().find {
            it.id == id
        }
        setValues(country!!)
    }

    private fun setValues(country: Country) {
        with (country) {
            iv_flag.setBackgroundResource(flag)
            tv_country_name.text = name
            tv_capital.text = capital
            tv_language.text = language
            tv_area.text = area.toString()
            tv_population.text = population.toString()
            tv_currency.text = currency
            tv_description.text = description
        }
    }
}