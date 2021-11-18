package com.example.lesson_8_lukin.presentation.mainWindow

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import com.example.lesson_8_lukin.data.db.DatabaseClient
import com.example.lesson_8_lukin.data.db.entity.NoteEntity

class mainWindowViewModel : ViewModel() {
    private val _notesLiveData = MutableLiveData<List<NoteEntity>>()
    val notesLiveData: LiveData<List<NoteEntity>> = _notesLiveData

    fun subscribeToNotes(context: Context) {
        viewModelScope.launch {
            DatabaseClient.getInstance(context).getNotesFlow().collect {
                _notesLiveData.postValue(it)
            }
        }
    }

    fun deleteNotes(context: Context, note: NoteEntity) {
        viewModelScope.launch {
            DatabaseClient.getInstance(context).deleteNote(listOf(note))
        }
    }

    fun archiveNote(context: Context, note: NoteEntity) {
        viewModelScope.launch {
            DatabaseClient.getInstance(context)
                .saveNotes(listOf(NoteEntity(note.id, note.title, note.note, note.color, archive = true)))
        }
    }

}