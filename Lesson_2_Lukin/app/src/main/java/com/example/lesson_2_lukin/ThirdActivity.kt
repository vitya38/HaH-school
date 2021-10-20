package com.example.lesson_2_lukin

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class ThirdActivity : AppCompatActivity() {

    companion object {
        fun createStartIntent(context: Context): Intent {
            return Intent(context, ThirdActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third)
    }
}