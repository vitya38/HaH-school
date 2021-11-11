package com.example.lesson_7_lukin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity(), FirstFragmentListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentContainerView, FirstFragment())
            .commit()
    }

    override fun click(icon: Int, picture: String?, name: String?, time: String, description: String?) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentContainerView, SecondFragment.newInstance(icon, picture, name, time, description))
            .commit()
    }

    override fun backClick() {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentContainerView, FirstFragment())
            .commit()
    }
}