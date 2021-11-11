package com.example.lesson_7_lukin


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.Locale
import kotlin.math.abs

class BridgeViewHolder(
    parent: ViewGroup,
    private val onItemCLick: (icon: Int, picture: String?, name: String?, time: String, desc: String?) -> Unit,
) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.card_bridge, parent, false)
) {
    companion object {
        private const val hour = 3600000
    }

    private val imageViewBridge by lazy { itemView.findViewById<ImageView>(R.id.imageViewBridge) }
    private val textViewBridgeName by lazy { itemView.findViewById<TextView>(R.id.textViewBridgeName) }
    private val textViewTime by lazy { itemView.findViewById<TextView>(R.id.textViewTime) }
    private val imageViewBell by lazy { itemView.findViewById<ImageView>(R.id.imageViewBell) }

    private fun time(times: List<Divorces>?): Int {
        val formaterDay = SimpleDateFormat("yyyy.MM.dd", Locale.getDefault())
        val format = SimpleDateFormat("yyyy.MM.dd HH:mm", Locale.getDefault())
        val now = System.currentTimeMillis()
        val today = formaterDay.format(now)
        var result = R.drawable.ic_brige_normal
        times?.forEach { divorces ->
            val timeFromListStart = format.parse(today + ' ' + divorces.start)?.time
            val timeFromListEnd = format.parse(today + ' ' + divorces.end)?.time
            if (now in timeFromListStart!!..timeFromListEnd!!) {
                return R.drawable.ic_brige_late
            } else if (abs(timeFromListStart - now) <= hour) {
                result = R.drawable.ic_brige_soon
            }
        }
        return result
    }

    private fun printDivorces(divorces: List<Divorces>?): String {
        var text = ""
        divorces?.forEach { item ->
            text += item.start
            text += '—'
            text += item.end
            text += "     "
        }
        return text
    }


    fun bind(bridge: Bridge) {

        val icon = time(bridge.divorces)
        imageViewBridge.setImageResource(icon)
        imageViewBell.visibility = View.VISIBLE
        textViewBridgeName.text = bridge.name
        val text = printDivorces(bridge.divorces)
        textViewTime.text = text
        var picture = bridge.photoOpenUrl
        if (icon == R.drawable.ic_brige_late) {
            picture = bridge.photoCloseUrl
        }
        itemView.setOnClickListener {
            onItemCLick(icon, picture, bridge.name, text, bridge.description)
        }
    }

}