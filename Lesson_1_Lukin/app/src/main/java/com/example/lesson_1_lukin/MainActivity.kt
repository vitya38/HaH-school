package com.example.lesson_1_lukin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button1 = findViewById<Button>(R.id.button1)
        val button2 = findViewById<Button>(R.id.button2)

        button1.setOnClickListener{
            val intent = SecondActivity.createStartIntent(this)
            startActivity(intent)
        }

        button2.setOnClickListener{
            val intent = ThirdActivity.createStartIntent(this)
            startActivity(intent)
        }
    }
}