package com.example.lesson_6_lukin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.example.lesson_6_lukin.thirdScreen.SetedFragment
import com.example.lesson_6_lukin.thirdScreen.ThirdFragmentListener

class MainActivity : AppCompatActivity(), ThirdFragmentListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragment = FirstFragment()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentContainerView, fragment)
            .commit()

        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottomNavigation)
        bottomNavigation.menu.findItem(R.id.page1).setOnMenuItemClickListener {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragmentContainerView, FirstFragment())
                .commit()
            it.setChecked(true)
            true
        }
        bottomNavigation.menu.findItem(R.id.page2).setOnMenuItemClickListener {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragmentContainerView, SecondFragment())
                .commit()
            it.setChecked(true)
            true
        }
        bottomNavigation.menu.findItem(R.id.page3).setOnMenuItemClickListener {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragmentContainerView, ThirdFragment())
                .commit()
            it.setChecked(true)
            true
        }
    }

    var bannerIsOpen = true
    val fragment = SetedFragment.newInstance()
    val button by lazy { findViewById<Button>(R.id.button) }
    override fun onButtonClick() {
        if (bannerIsOpen) {
            button.text = getString(R.string.hide_banner)
            bannerIsOpen = false
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragmentContainerView2, fragment)
                .addToBackStack(null)
                .commit()
        } else {
            button.text = getString(R.string.show_banner)
            bannerIsOpen = true
            supportFragmentManager
                .beginTransaction()
                .remove(fragment)
                .addToBackStack(null)
                .commit()
        }
    }

    override fun onImageClick(catText: String) {
        Toast.makeText(this, catText, Toast.LENGTH_SHORT).show()
    }
}