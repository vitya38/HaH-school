package com.example.lesson_8_lukin.data.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lesson_8_lukin.R
import com.example.lesson_8_lukin.data.db.entity.NoteEntity
import com.google.android.material.card.MaterialCardView

class NoteViewHolder(
    parent: ViewGroup,
    private val onItemCLick: (NoteEntity) -> Unit,
    private val onLongItemClick: (NoteEntity) -> Unit,
) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.card, parent, false)
) {
    private val textViewTitle by lazy { itemView.findViewById<TextView>(R.id.textViewTitle) }
    private val textViewText by lazy { itemView.findViewById<TextView>(R.id.textViewText) }
    private val materialCardView by lazy { itemView.findViewById<MaterialCardView>(R.id.materialCardView) }

    fun bind(note: NoteEntity) {
        textViewTitle.text = note.title
        textViewText.text = note.note
        materialCardView.setBackgroundColor(note.color)
        itemView.setOnClickListener {
            onItemCLick(note)
        }
        itemView.setOnLongClickListener {
            onLongItemClick(note)
            true
        }
    }
}