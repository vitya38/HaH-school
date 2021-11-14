package com.example.lesson_7_lukin.presentation.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.lesson_7_lukin.presentation.fragments.FirstFragment
import com.example.lesson_7_lukin.presentation.fragments.FirstFragmentListener
import com.example.lesson_7_lukin.R
import com.example.lesson_7_lukin.presentation.fragments.SecondFragment
import com.example.lesson_7_lukin.data.model.BridgeToSend

class MainActivity : AppCompatActivity(), FirstFragmentListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentContainerView, FirstFragment())
            .addToBackStack(null)
            .commit()
    }

    override fun click(bridge: BridgeToSend) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentContainerView, SecondFragment.newInstance(bridge))
            .addToBackStack(null)
            .commit()
    }

    override fun backClick() {
        supportFragmentManager
            .popBackStack()
    }
}