package com.example.lesson_7_lukin.data.viewholder

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lesson_7_lukin.data.model.Bridge
import com.example.lesson_7_lukin.data.model.BridgeToSend

class BridgeAdapter : RecyclerView.Adapter<BridgeViewHolder>() {

    private val bridges = mutableListOf<Bridge>()

    lateinit var onItemCLick: (BridgeToSend) -> Unit
    lateinit var onBellClick: () -> Unit

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BridgeViewHolder {
        return BridgeViewHolder(parent, onItemCLick, onBellClick)

    }

    override fun onBindViewHolder(holder: BridgeViewHolder, position: Int) {
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