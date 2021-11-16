package com.example.lesson_8_lukin.data.db

import android.content.Context
import androidx.room.Room
import com.example.lesson_8_lukin.data.db.entity.NoteEntity
import kotlinx.coroutines.flow.Flow

class DatabaseClient private constructor(
    context: Context,
) {
    companion object {
        private var instance: DatabaseClient? = null

        fun getInstance(context: Context): DatabaseClient {
            return instance ?: run {
                val newInstance = DatabaseClient(context)
                instance = newInstance
                newInstance
            }
        }
    }

    private val db = Room.databaseBuilder(context, AppDatabase::class.java, AppDatabase.DATABASE_NAME).build()

    suspend fun saveNotes(notes: List<NoteEntity>) {
        db.noteDao().saveNotes(*notes.toTypedArray())
    }

    fun getNotesFlow(): Flow<List<NoteEntity>> {
        return db.noteDao().getNotesFlow()
    }

    suspend fun deleteNote(notes: List<NoteEntity>) {
        db.noteDao().deleteNote(*notes.toTypedArray())
    }

}