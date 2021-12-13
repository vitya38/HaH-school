package com.example.lesson_12_lukin.data.model

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.example.lesson_12_lukin.R
import com.example.lesson_12_lukin.databinding.ViewBridgeBinding
import java.text.SimpleDateFormat
import java.util.Locale
import kotlin.math.abs

class BridgeView : LinearLayout {
    constructor(context: Context) : super(context) {
        init(context, null)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(context, attrs)
    }

    private val binding = ViewBridgeBinding.inflate(LayoutInflater.from(context), this)

    private fun init(context: Context, attrs: AttributeSet?) {
        orientation = HORIZONTAL
        val a = context.theme.obtainStyledAttributes(attrs, R.styleable.BridgeView, 0, 0)
        try {
        } finally {
            a.recycle()
        }
    }

    fun setBridge(bridge: Bridge) {
        binding.bridgeName.text = bridge.name.orEmpty()
        binding.bridgeTime.text = printDivorces(bridge.divorces)
        val bridgeIcon = (time(bridge.divorces))
        binding.bridgeIcon.setImageResource(bridgeIcon)
    }

    private fun printDivorces(divorces: List<Divorces>?): String {
        val builder = StringBuilder()
        divorces?.forEach { item ->
            builder.append(item.start)
            builder.append('—')
            builder.append(item.end)
            builder.append("     ")
        }
        return builder.toString()
    }

    private fun time(times: List<Divorces>?): Int {
        val hour = 3600000
        val formaterDay = SimpleDateFormat("yyyy.MM.dd", Locale.getDefault())
        val format = SimpleDateFormat("yyyy.MM.dd HH:mm", Locale.getDefault())
        val now = System.currentTimeMillis()
        val today = formaterDay.format(now)
        var result = R.drawable.ic_brige_normal
        times?.forEach { divorces ->
            val builder = StringBuilder()
            builder.append(today)
            builder.append(' ')
            builder.append(divorces.start)
            val timeFromListStart = format.parse(builder.toString())?.time
            builder.clear()
            builder.append(today)
            builder.append(' ')
            builder.append(divorces.end)
            val timeFromListEnd = format.parse(builder.toString())?.time
            if (now in timeFromListStart!!..timeFromListEnd!!) {
                return R.drawable.ic_brige_late
            } else if (abs(timeFromListStart - now) <= hour) {
                result = R.drawable.ic_brige_soon
            }
        }
        return result
    }
}