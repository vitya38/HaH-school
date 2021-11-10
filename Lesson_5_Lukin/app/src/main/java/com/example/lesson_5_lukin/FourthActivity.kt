package com.example.lesson_5_lukin

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.google.android.material.button.MaterialButton
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class FourthActivity : AppCompatActivity() {

    companion object {
        const val DATE = "date"

        fun createStartIntent(context: Context, millis: Long): Intent {
            return Intent(context, FourthActivity::class.java).setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP).apply {
                putExtra(DATE, millis)
            }
        }
    }

    private val textView1 by lazy { findViewById<TextView>(R.id.textView1) }

    private fun time(intent: Intent?) {
        val formater = SimpleDateFormat("dd.MM.yyyy HH:mm:ss", Locale.getDefault())
        val date = Date(intent?.getSerializableExtra(DATE).toString().toLong())
        val formated = formater.format(date)
        textView1.text = formated
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fourth)
        time(intent)
        findViewById<MaterialButton>(R.id.buttonAgain4).setOnClickListener {
            startActivity(createStartIntent(this, System.currentTimeMillis()))
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        time(intent)
    }
}