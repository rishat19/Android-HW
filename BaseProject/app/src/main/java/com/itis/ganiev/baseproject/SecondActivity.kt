package com.itis.ganiev.baseproject

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        textView.text = intent.getStringExtra(Intent.EXTRA_TEXT)

        button1.setOnClickListener {
            sendButtonNumber(1)
        }
        button2.setOnClickListener {
            sendButtonNumber(2)
        }
    }

    private fun sendButtonNumber(index:Int) {
        setResult(Activity.RESULT_OK, Intent().putExtra("key", "$index"))
        finish()
    }

}