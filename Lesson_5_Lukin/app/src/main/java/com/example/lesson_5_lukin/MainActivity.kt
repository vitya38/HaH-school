package com.example.lesson_5_lukin

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.button.MaterialButton

class MainActivity : AppCompatActivity() {

    companion object {
        fun createStartIntent(context: Context): Intent {
            return Intent(context, MainActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        findViewById<MaterialButton>(R.id.button2).setOnClickListener {
            startActivity(SecondActivity.createStartIntent(this))
        }

        findViewById<MaterialButton>(R.id.button4).setOnClickListener {
            startActivity(FourthActivity.createStartIntent(this, System.currentTimeMillis()))
        }
    }
}