package com.example.lesson_4_lukin

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lesson_4_lukin.model.BaseInfoItem
import com.example.lesson_4_lukin.model.BaseInfoItemViewHolder
import com.example.lesson_4_lukin.model.DetailInfoItem
import com.example.lesson_4_lukin.model.DetailInfoItemViewHolder
import com.example.lesson_4_lukin.model.MixedInfoItemViewHolder
import java.lang.Exception

class MainAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    companion object {
        private const val BASE_TYPE = 0
        private const val DETAIL_TYPE = 1
        private const val MIXED_TYPE = 2
    }

    private val items = mutableListOf<BaseInfoItem>()

    lateinit var onItemClick: (String) -> Unit

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            BASE_TYPE -> BaseInfoItemViewHolder(parent, onItemClick)
            DETAIL_TYPE -> DetailInfoItemViewHolder(parent, onItemClick)
            MIXED_TYPE -> MixedInfoItemViewHolder(parent, onItemClick)
            else -> throw Exception("Unsupported view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            BASE_TYPE -> (holder as BaseInfoItemViewHolder).bind(items[position])
            DETAIL_TYPE -> (holder as DetailInfoItemViewHolder).bind(items[position] as DetailInfoItem)
            MIXED_TYPE -> (holder as MixedInfoItemViewHolder).bind(items[position] as DetailInfoItem)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun setItems(items: List<BaseInfoItem>) {
        this.items.apply {
            clear()
            addAll(items)
        }
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        return if (items[position] is DetailInfoItem && items[position + 1] is DetailInfoItem) {
            DETAIL_TYPE
        } else if (items[position] is DetailInfoItem) {
            MIXED_TYPE
        } else {
            BASE_TYPE
        }
    }
}