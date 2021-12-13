package com.example.lesson_12_lukin.presentation.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lesson_12_lukin.data.model.BridgeView
import com.example.lesson_12_lukin.R
import com.example.lesson_12_lukin.data.model.Bridge

class BridgeListViewHolder(
    parent: ViewGroup,
    private val onItemClick: (String) -> Unit
) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.item_bridge, parent, false)
) {

    fun bind(bridge: Bridge) {
        (itemView as BridgeView).setBridge(bridge)
        itemView.setOnClickListener {
            bridge.id?.let { id -> onItemClick(id) }
        }
    }
}