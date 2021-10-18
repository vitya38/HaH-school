package com.example.lesson_1_lukin

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class SecondActivity : AppCompatActivity() {

    companion object {
        fun createStartIntent(context: Context): Intent {
            return Intent(context, SecondActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val button1 = findViewById<Button>(R.id.button)
        val button2 = findViewById<Button>(R.id.button3)
        val editText = findViewById<EditText>(R.id.editText)
        val textView = findViewById<TextView>(R.id.textView)
        val people = sortedSetOf<String>()

        button1.setOnClickListener {
            val name = editText.text.toString()
            people.add(name)
            editText.text.clear()
        }

        button2.setOnClickListener {
            textView.setText("")
            var result = ""
            people.forEach {
                result += it + '\n'
            }
            textView.setText(result)
        }
    }
}