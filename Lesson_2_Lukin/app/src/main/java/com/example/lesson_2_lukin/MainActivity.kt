package com.example.lesson_2_lukin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button1 = findViewById<Button>(R.id.button)

        button1.setOnClickListener {
            val intent = SecondActivity.createStartIntent(this)
            startActivity(intent)
        }

        val button2 = findViewById<Button>(R.id.button3)

        button2.setOnClickListener {
            val intent = ThirdActivity.createStartIntent(this)
            startActivity(intent)
        }
    }
}