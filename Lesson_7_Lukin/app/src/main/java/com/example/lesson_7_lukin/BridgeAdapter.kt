package com.example.lesson_7_lukin

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import java.lang.Exception

class BridgeAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    companion object{
        private const val TYPE = 0
    }

    private val bridges = mutableListOf<Bridge>()

    lateinit var onItemCLick: (icon: Int, picture: String?, name: String?, time: String, desc: String?)->Unit

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType){
            TYPE -> BridgeViewHolder(parent, onItemCLick)
            else -> throw Exception("Unsupported view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)){
            TYPE -> (holder as BridgeViewHolder).bind(bridges[position])
        }
    }

    override fun getItemCount(): Int {
        return bridges.size
    }

    fun setItems (items: List<Bridge>){
        this.bridges.apply {
            clear()
            addAll(items)
        }
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        return TYPE
    }
}