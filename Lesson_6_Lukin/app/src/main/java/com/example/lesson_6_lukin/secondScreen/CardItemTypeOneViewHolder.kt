package com.example.lesson_6_lukin.secondScreen

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lesson_6_lukin.R

class CardItemTypeOneViewHolder(
    parent: ViewGroup
) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.item_two_card_type1, parent, false)
) {
    private val imageView by lazy { itemView.findViewById<ImageView>(R.id.imageViewMain) }
    private val textViewMain by lazy { itemView.findViewById<TextView>(R.id.textViewMain) }
    private val textViewCodeText by lazy { itemView.findViewById<TextView>(R.id.codeText) }

    fun bind(base: CardItemTypeOne) {
        imageView.setImageResource(base.icon)
        textViewMain.text = base.cardName
        textViewCodeText.text = base.codeNumber
    }
}