package com.example.lesson_5_lukin

import android.content.Context
import android.content.Intent
import android.icu.text.SimpleDateFormat
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.google.android.material.button.MaterialButton
import java.util.Date

class FourthActivity : AppCompatActivity() {

    companion object {
        const val DATE = "date"

        fun createStartIntent(context: Context): Intent {
            return Intent(context, FourthActivity::class.java).setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
        }
    }

    val textView1 by lazy { findViewById<TextView>(R.id.textView1) }

    fun time(intent: Intent?) {

        val formater = SimpleDateFormat("dd.MM.yyyy HH:mm:ss")
        val date = Date(intent?.getSerializableExtra(DATE).toString().toLong())
        val formated = formater.format(date)
        textView1.setText(formated)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fourth)
        time(intent)
        findViewById<MaterialButton>(R.id.buttonAgain4).setOnClickListener {
            startActivity(FourthActivity.createStartIntent(this).apply {
                putExtra(MainActivity.DATE, System.currentTimeMillis())
            })
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        time(intent)
    }
}