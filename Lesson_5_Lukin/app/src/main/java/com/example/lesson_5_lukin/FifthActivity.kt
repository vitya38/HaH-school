package com.example.lesson_5_lukin

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.widget.EditText
import android.widget.TextView
import com.google.android.material.button.MaterialButton
import model.Data

class FifthActivity : AppCompatActivity() {

    companion object {
        const val TEXT = "text"
        const val DATA = "data"

        fun createStartIntent(context: Context): Intent {
            return Intent(context, FifthActivity::class.java)
        }
    }

    private val editText by lazy { findViewById<EditText>(R.id.editText) }
    private val textView by lazy { findViewById<TextView>(R.id.textView2) }
    private val saveData by lazy { Data(editText.text.toString()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fifth)

        if (savedInstanceState != null && savedInstanceState.containsKey(DATA)) {
            val oldData = savedInstanceState.getParcelable<Parcelable>(DATA) as Data
            textView.text = oldData.value
        }
        findViewById<MaterialButton>(R.id.buttonDeliverResult).setOnClickListener {
            val text = editText.text.toString()
            setResult(Activity.RESULT_OK, Intent().apply { putExtra(TEXT, text) })
            finish()
        }
        findViewById<MaterialButton>(R.id.buttonSave).setOnClickListener {
            textView.text = saveData.value
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val oldData = savedInstanceState.getParcelable<Parcelable>(DATA) as Data
        textView.text = oldData.value
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putParcelable(DATA, saveData)
        super.onSaveInstanceState(outState)
    }
}