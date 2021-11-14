package com.example.lesson_7_lukin.data.viewholder

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lesson_7_lukin.data.model.Bridge
import com.example.lesson_7_lukin.data.model.BridgeToSend

class BridgeAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val bridges = mutableListOf<Bridge>()

    lateinit var onItemCLick: (BridgeToSend) -> Unit

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return BridgeViewHolder(parent, onItemCLick)

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as BridgeViewHolder).bind(bridges[position])
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