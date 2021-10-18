package com.example.lesson_1_lukin

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class ThirdActivity : AppCompatActivity() {

    companion object {
        fun createStartIntent(context: Context): Intent {
            return Intent(context, ThirdActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third)

        val button = findViewById<Button>(R.id.button4)
        val editText = findViewById<EditText>(R.id.editText1)
        val textView = findViewById<TextView>(R.id.textView1)

        fun split(s: String): Student {
            val parts = s.split(" ")
            val person = Student(
                System.currentTimeMillis(), parts[0], parts[1],
                parts[2], parts[3].toInt()
            )
            return person
        }

        val people = HashMap<Int, Student>()
        var i = 0
        editText.setOnKeyListener { _, keyCode, keyEvent ->
            if (keyEvent.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                people.put(i++, split(editText.text.toString()))
                editText.setText("")
                return@setOnKeyListener true
            }
            false
        }

        button.setOnClickListener {
            textView.setText("")
            var result = ""
            people.forEach { (_, value) ->
                result += value.print() + '\n'
            }
            textView.setText(result)
        }
    }
}