package com.example.lesson_8_lukin.presentation.second

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lesson_8_lukin.data.db.DatabaseClient
import com.example.lesson_8_lukin.data.db.entity.NoteEntity
import kotlinx.coroutines.launch
import kotlin.random.Random

class SecondViewModel : ViewModel() {

    fun createNote(context: Context, title: String, text: String, color: Int, id: Int?) {
        val idNote = id ?: Random.nextInt()
        val note = NoteEntity(
            id = idNote,
            title = title,
            note = text,
            color = color,
            archive = false,
        )
        viewModelScope.launch {
            DatabaseClient.getInstance(context).saveNotes(listOf(note))
        }
    }
}