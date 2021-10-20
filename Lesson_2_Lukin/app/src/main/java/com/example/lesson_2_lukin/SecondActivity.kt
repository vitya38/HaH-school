package com.example.lesson_2_lukin

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat

class SecondActivity : AppCompatActivity() {

    companion object {
        fun createStartIntent(context: Context): Intent {
            return Intent(context, SecondActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val textView = findViewById<TextView>(R.id.textView2)
        textView.setText(R.string.pushkin)

        val myImage = ResourcesCompat.getDrawable(resources, R.drawable.picture, null)

        val imageView = findViewById<ImageView>(R.id.imageView)
        imageView.setImageDrawable(myImage)

        val button = findViewById<Button>(R.id.button2)
        button.setText(R.string.buttonName)
    }
}