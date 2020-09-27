package com.itis.ganiev.baseproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        UsersRepository.users.add(User(
            "angelina_300102@mail.ru",
            "3019AnEv",
            "Angelina Evsikova",
            R.drawable.angelina,
            "Школа № 91",
            "Казань",
            "Казань, респ. Татарстан",
            "3019 человек"))
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}
