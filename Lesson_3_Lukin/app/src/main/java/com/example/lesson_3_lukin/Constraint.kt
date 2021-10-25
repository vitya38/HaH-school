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

        val myAccount = Account(
            "Анастасия", "Антонина", "anykee.box@gmail.ru",
            "HIE023UOI88", "Санкт-Петербург", "7898769", "Специалист"
        )

        var includeView = findViewById<View>(R.id.include1)
        var textView = includeView.findViewById<TextView>(R.id.text1)

        textView.setText(R.string.name)
        textView = includeView.findViewById<TextView>(R.id.text2)
        textView.setText(myAccount.name)

        includeView = findViewById<View>(R.id.include2)
        textView = includeView.findViewById<TextView>(R.id.text1)
        textView.setText(R.string.surname)
        textView = includeView.findViewById<TextView>(R.id.text2)
        textView.setText(myAccount.surname)

        includeView = findViewById<View>(R.id.include3)
        textView = includeView.findViewById<TextView>(R.id.text1)
        textView.setText(R.string.email)
        textView = includeView.findViewById<TextView>(R.id.text2)
        textView.setText(myAccount.email)

        includeView = findViewById<View>(R.id.include4)
        textView = includeView.findViewById<TextView>(R.id.text1)
        textView.setText(R.string.login)
        textView = includeView.findViewById<TextView>(R.id.text2)
        textView.setText(myAccount.login)

        textView = findViewById<TextView>(R.id.textWithIcon1)
        textView.setText(R.string.region)
        textView = findViewById<TextView>(R.id.textWithIcon2)
        textView.setText(myAccount.region)

        textView = findViewById<TextView>(R.id.textExit)
        textView.setText(R.string.exit)
        textView = findViewById(R.id.card)
        textView.setText("Карта №" + myAccount.cardNumber + " " + myAccount.job)

        val imageButton = findViewById<ImageButton>(R.id.imageButton1)
        imageButton.setOnClickListener {
            val toast = Toast.makeText(applicationContext, R.string.toast_message, Toast.LENGTH_SHORT)
            toast.show()
        }

        val textButton = findViewById<TextView>(R.id.textExit)
        textButton.setOnClickListener {
            val Toast = Toast.makeText(applicationContext, R.string.toast_message, Toast.LENGTH_SHORT)
            Toast.show()
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