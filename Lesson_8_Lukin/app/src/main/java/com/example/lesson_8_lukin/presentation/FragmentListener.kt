package com.example.lesson_8_lukin.presentation

import com.example.lesson_8_lukin.data.db.entity.NoteEntity

interface FragmentListener {
    fun backClick()
    fun newNoteClick()
    fun noteClick(note: NoteEntity)
}