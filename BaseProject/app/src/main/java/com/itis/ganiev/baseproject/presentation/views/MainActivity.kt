package com.itis.ganiev.baseproject.presentation.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.itis.ganiev.baseproject.R
import dagger.hilt.android.AndroidEntryPoint
import moxy.MvpAppCompatActivity

@AndroidEntryPoint
class MainActivity : MvpAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setFragment(SearchFragment.newInstance())
    }

    private fun setFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }

}
