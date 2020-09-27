package com.itis.ganiev.baseproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (intent != null) {
            photo.setImageResource(intent.getIntExtra("photoId", R.drawable.user))
            name.text = intent.getStringExtra("name")
            if (intent.getBooleanExtra("flag", false)) {
                schoolInfo.text = intent.getStringExtra("school")
                houseInfo.text = intent.getStringExtra("house")
                placeInfo.text = intent.getStringExtra("place")
                subscriptionsInfo.text = intent.getStringExtra("subscriptions")
            }
        }

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
