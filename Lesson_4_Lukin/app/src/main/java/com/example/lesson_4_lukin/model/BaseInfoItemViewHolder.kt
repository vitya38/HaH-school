package com.example.lesson_4_lukin.model

import android.content.Context
import android.view.Display
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.isVisible
import androidx.core.view.marginTop
import androidx.recyclerview.widget.RecyclerView
import com.example.lesson_4_lukin.R
import com.google.android.material.card.MaterialCardView
import org.w3c.dom.Text

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
        if (base.cardText == "") {
            textViewCardText.isVisible = false
        } else
            textViewCardText.text = base.cardText
    }
}
