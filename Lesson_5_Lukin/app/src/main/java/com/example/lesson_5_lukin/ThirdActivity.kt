package com.example.lesson_5_lukin

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.button.MaterialButton
import com.google.android.material.snackbar.Snackbar
import model.FifthActivityContract

class ThirdActivity : AppCompatActivity() {

    companion object {
        fun createStartIntent(context: Context): Intent {
            return Intent(context, ThirdActivity::class.java)
        }
    }

    private val view by lazy { findViewById<ConstraintLayout>(R.id.constraint) }

    private val launcher = registerForActivityResult(FifthActivityContract()) {
        Snackbar.make(view, it.toString(), Snackbar.LENGTH_SHORT).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third)

        findViewById<MaterialButton>(R.id.button1).setOnClickListener {
            startActivity(MainActivity.createStartIntent(this))
        }

        findViewById<MaterialButton>(R.id.button5).setOnClickListener {
            launcher.launch(Unit)
        }
    }
}