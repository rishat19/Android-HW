package com.itis.ganiev.baseproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        editInfo.setOnClickListener {
            if (houseInfo.visibility == View.VISIBLE) {
                houseInfo.visibility = View.INVISIBLE
                editHouseInfo.setText(houseInfo.text)
                editHouseInfo.visibility = View.VISIBLE
            } else {
                editHouseInfo.visibility = View.INVISIBLE
                houseInfo.text = editHouseInfo.text
                houseInfo.visibility = View.VISIBLE
            }
        }
    }
}
