package com.example.lesson_8_lukin.data.db.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.lesson_8_lukin.data.db.dao.NoteDao
import kotlinx.parcelize.Parcelize

@Entity(tableName = NoteDao.TABLE_NAME)
@Parcelize
data class NoteEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "note") val note: String,
    @ColumnInfo(name = "color") val color:Int,
    @ColumnInfo(name = "is_archive") val archive:Boolean,
) : Parcelable
