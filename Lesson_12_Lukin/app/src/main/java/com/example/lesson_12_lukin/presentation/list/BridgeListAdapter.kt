package com.example.lesson_12_lukin.presentation.list

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lesson_12_lukin.data.model.Bridge

class BridgeListAdapter : RecyclerView.Adapter<BridgeListViewHolder>() {

    private val bridges = mutableListOf<Bridge>()
    lateinit var onItemClick: (String) -> Unit

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BridgeListViewHolder {
        return BridgeListViewHolder(parent, onItemClick)
    }

    override fun onBindViewHolder(holder: BridgeListViewHolder, position: Int) {
        holder.bind(bridges[position])
    }

    override fun getItemCount(): Int {
        return bridges.size
    }

    fun setItems(items: List<Bridge>) {
        this.bridges.apply {
            clear()
            addAll(items)
        }
        notifyDataSetChanged()
    }
}