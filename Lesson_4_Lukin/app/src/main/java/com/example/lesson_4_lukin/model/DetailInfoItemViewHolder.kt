package com.example.lesson_4_lukin.model

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.lesson_4_lukin.R

class DetailInfoItemViewHolder(
    parent: ViewGroup,
    private val onItemClick: (String) -> Unit,
) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.item_detail, parent, false)
) {
    private val imageView by lazy { itemView.findViewById<ImageView>(R.id.imageView2) }
    private val textViewCardName by lazy { itemView.findViewById<TextView>(R.id.textViewDetail1) }
    private val textViewCardText by lazy { itemView.findViewById<TextView>(R.id.textViewDetail2) }

    fun bind(detail: DetailInfoItem) {
        itemView.setOnClickListener {
            onItemClick(detail.cardName)
        }
        imageView.setImageResource(detail.icon)
        textViewCardName.text = detail.cardName
        textViewCardText.text = detail.cardText
        if (detail.isRedText)
            textViewCardText.setTextColor(Color.parseColor("#FF4242"))
        else
            textViewCardText.setTextColor(Color.parseColor("#888888"))
    }
}