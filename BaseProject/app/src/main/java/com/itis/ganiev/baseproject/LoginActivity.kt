package com.itis.ganiev.baseproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        enter.setOnClickListener {
            if (!et_name.text.isNullOrEmpty() && !et_email.text.isNullOrEmpty() && !et_password.text.isNullOrEmpty()) {
                val emailRegex = Regex(pattern = "^(?![\\-.])[-0-9A-z.]+(?<![\\-.])@((?!-)[A-Za-z0-9-]{1,63}(?<!-)\\.)+[A-Za-z]{2,6}$")
                val passwordRegex = Regex(pattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z]{6,}$")
                if (emailRegex.containsMatchIn(et_email.text!!) && passwordRegex.containsMatchIn(et_password.text!!)) {
                    var flag = true
                    UsersRepository.users.forEach {
                        if (et_email.text.toString() == it.email && et_password.text.toString() == it.password) {
                            flag = false
                            val intent = Intent(this, MainActivity::class.java).apply {
                                putExtra("name", it.name)
                                putExtra("photoId", it.photoId)
                                putExtra("flag", true)
                                putExtra("school", it.school)
                                putExtra("house", it.house)
                                putExtra("place", it.place)
                                putExtra("subscriptions", it.subscriptions)
                            }
                            startActivity(intent)
                            finish()
                        }
                    }
                    if (flag) {
                        Toast.makeText(this, "Ошибка: такого пользоателя не существует.", Toast.LENGTH_LONG).show()
                    }
                } else {
                    Toast.makeText(this, "Ошибка: неверно введены данные.", Toast.LENGTH_LONG).show()
                }
            } else {
                Toast.makeText(this, "Ошибка: есть незаполненные поля.", Toast.LENGTH_LONG).show()
            }
        }

        create.setOnClickListener {
            if (!et_name.text.isNullOrEmpty() && !et_email.text.isNullOrEmpty() && !et_password.text.isNullOrEmpty()) {
                val emailRegex = Regex(pattern = "^(?![\\-.])[-0-9A-z.]+(?<![\\-.])@((?!-)[A-Za-z0-9-]{1,63}(?<!-)\\.)+[A-Za-z]{2,6}$")
                val passwordRegex = Regex(pattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z]{6,}$")
                if (emailRegex.containsMatchIn(et_email.text!!) && passwordRegex.containsMatchIn(et_password.text!!)) {
                    val intent = Intent(this, MainActivity::class.java).apply {
                        putExtra("name", et_name.text.toString())
                        putExtra("flag", false)
                    }
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(this, "Ошибка: неверно введены данные.", Toast.LENGTH_LONG).show()
                }
            } else {
                Toast.makeText(this, "Ошибка: есть незаполненные поля.", Toast.LENGTH_LONG).show()
            }
        }

    }
}
