package com.itis.ganiev.baseproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rv_countries.adapter = CountryAdapter(
            CountryRepository.getCountries()
        ) { id ->
            val intent = Intent(this, CountryInfoActivity::class.java)
            intent.putExtra("ID", id)
            startActivity(intent)
        }

        rv_countries.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

    }
}
