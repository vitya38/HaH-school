package com.example.lesson_3_lukin

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast

class Constraint : AppCompatActivity() {

    companion object {
        fun createStartIntent(context: Context): Intent {
            return Intent(context, Constraint::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_constraint)

        var include_view = findViewById<View>(R.id.include1)
        var textView = include_view.findViewById<TextView>(R.id.text1)
        textView.setText(R.string.name)
        textView = include_view.findViewById<TextView>(R.id.text2)
        textView.setText(R.string.anastasya)

        include_view = findViewById<View>(R.id.include2)
        textView = include_view.findViewById<TextView>(R.id.text1)
        textView.setText(R.string.surname)
        textView = include_view.findViewById<TextView>(R.id.text2)
        textView.setText(R.string.antonina)

        include_view = findViewById<View>(R.id.include3)
        textView = include_view.findViewById<TextView>(R.id.text1)
        textView.setText(R.string.email)
        textView = include_view.findViewById<TextView>(R.id.text2)
        textView.setText(R.string.email_adress)

        include_view = findViewById<View>(R.id.include4)
        textView = include_view.findViewById<TextView>(R.id.text1)
        textView.setText(R.string.login)
        textView = include_view.findViewById<TextView>(R.id.text2)
        textView.setText(R.string.log_name)

        textView = findViewById<TextView>(R.id.text_with_icon1)
        textView.setText(R.string.region)
        textView = findViewById<TextView>(R.id.text_with_icon2)
        textView.setText(R.string.region_name)

        textView = findViewById<TextView>(R.id.text_exit)
        textView.setText(R.string.exit)

        val imageButton1 = findViewById<ImageButton>(R.id.image_button1)
        imageButton1.setOnClickListener {
            val toast = Toast.makeText(applicationContext, R.string.toast_message, Toast.LENGTH_SHORT)
            toast.show()
        }

        val imageButton2 = findViewById<ImageButton>(R.id.image_button2)
        imageButton2.setOnClickListener {
            val toast = Toast.makeText(applicationContext, R.string.toast_message, Toast.LENGTH_SHORT)
            toast.show()
        }

        val toolbar = findViewById<com.google.android.material.appbar.MaterialToolbar>(R.id.toolbar)
        toolbar.setNavigationOnClickListener {
            val toast = Toast.makeText(applicationContext, R.string.toast_message, Toast.LENGTH_SHORT)
            toast.show()
        }

        toolbar.menu.findItem(R.id.pen).setOnMenuItemClickListener {
            val toast = Toast.makeText(applicationContext, R.string.toast_message, Toast.LENGTH_SHORT)
            toast.show()
            true
        }
    }
}