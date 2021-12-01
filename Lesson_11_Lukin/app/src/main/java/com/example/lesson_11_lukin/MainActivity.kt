package com.example.lesson_11_lukin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    private val graphView by lazy { findViewById<GraphView>(R.id.graphView) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        graphView.setData(9)
        graphView.setOnClickListener {
            graphView.myAnimation()
        }
    }
}