package com.example.lesson_8_lukin.presentation.mainWindow.viewholder

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lesson_8_lukin.data.db.entity.NoteEntity


class NoteAdapter : RecyclerView.Adapter<NoteViewHolder>() {

    private val notes = mutableListOf<NoteEntity>()
    lateinit var onItemClick: (NoteEntity) -> Unit
    lateinit var onLongItemClick: (NoteEntity) -> Unit
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(parent, onItemClick, onLongItemClick)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(notes[position])
    }

    override fun getItemCount(): Int {
        return notes.size
    }

    fun setItems(items: List<NoteEntity>) {
        this.notes.apply {
            clear()
            addAll(items)
        }
        notifyDataSetChanged()
    }
}