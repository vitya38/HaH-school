package com.example.lesson_4_lukin.model

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.lesson_4_lukin.R

class BaseInfoItemViewHolder(
    parent: ViewGroup,
    private val onItemClick: (String) -> Unit,
) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.item_base, parent, false)
) {
    private val imageView by lazy { itemView.findViewById<ImageView>(R.id.imageView1) }
    private val textViewCardName by lazy { itemView.findViewById<TextView>(R.id.textViewBase1) }
    private val textViewCardText by lazy { itemView.findViewById<TextView>(R.id.textViewBase2) }

    fun bind(base: BaseInfoItem) {
        itemView.setOnClickListener {
            onItemClick(base.cardName)
        }
        imageView.setImageResource(base.icon)
        textViewCardName.text = base.cardName
        textViewCardText.isVisible = false
    }
}
