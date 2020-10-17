package com.itis.ganiev.baseproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.imageview.ShapeableImageView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var selectedImageButton : ShapeableImageView? =  null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        imageButton1.setOnClickListener {
            if (selectedImageButton != imageButton1) {
                setButtonState(imageButton1)
                setFragment(Fragment1.newInstance())
            }
        }
        imageButton2.setOnClickListener {
            if (selectedImageButton != imageButton2) {
                setButtonState(imageButton2)
                setFragment(Fragment2.newInstance())
            }
        }
        imageButton3.setOnClickListener {
            if (selectedImageButton != imageButton3) {
                setButtonState(imageButton3)
                setFragment(Fragment3.newInstance())
            }
        }
        imageButton4.setOnClickListener {
            if (selectedImageButton != imageButton4) {
                setButtonState(imageButton4)
                setFragment(Fragment4.newInstance())
            }
        }
        imageButton5.setOnClickListener {
            if (selectedImageButton != imageButton5) {
                setButtonState(imageButton5)
                setFragment(Fragment5.newInstance())
            }
        }
    }

    private fun setButtonState(imageView: ShapeableImageView) {
        selectedImageButton?.isSelected = false
        imageView.isSelected = true
        selectedImageButton = imageView
    }

    private fun setFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .setCustomAnimations(R.animator.enter, R.animator.exit)
            .replace(R.id.fragmentContainer, fragment)
            .commit()
    }

}
