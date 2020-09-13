package com.itis.ganiev.baseproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val animal = Animal("Masha", 5)
        println(animal.info())
        val whale = Whale("Dasha", 7)
        println(whale.info())
        println(whale.swim())
    }

}
