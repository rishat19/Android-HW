package com.itis.ganiev.baseproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bnv_main.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_text -> {
                    setFragment(Fragment1.newInstance())
                    true
                }
                R.id.navigation_list -> {
                    setFragment(Fragment2.newInstance())
                    true
                }
                R.id.navigation_cards -> {
                    setFragment(Fragment3.newInstance())
                    true
                }
                else -> false
            }
        }

        bnv_main.setOnNavigationItemReselectedListener {}
    }

    private fun setFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .commit()
    }

}
