package com.example.lesson_6_lukin.secondScreen

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import java.lang.Exception

class Adapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    companion object {
        private const val FIRST_TYPE = 0
        private const val SECOND_TYPE = 1
    }

    private val items = mutableListOf<CardItemTypeOne>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            FIRST_TYPE -> CardItemTypeOneViewHolder(parent)
            SECOND_TYPE -> CardItemTypeTwoViewHolder(parent)
            else -> throw Exception("Unsupported view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            FIRST_TYPE -> (holder as CardItemTypeOneViewHolder).bind(items[position])
            SECOND_TYPE -> (holder as CardItemTypeTwoViewHolder).bind(items[position] as CardItemTypeTwo)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun setItems(items: List<CardItemTypeOne>) {
        this.items.apply {
            clear()
            addAll(items)
        }
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        return if (items[position] is CardItemTypeTwo) {
            SECOND_TYPE
        } else {
            FIRST_TYPE
        }
    }
}