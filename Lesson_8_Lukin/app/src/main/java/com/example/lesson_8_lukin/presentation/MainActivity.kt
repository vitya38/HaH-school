package com.example.lesson_8_lukin.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.lesson_8_lukin.R
import com.example.lesson_8_lukin.data.db.entity.NoteEntity
import com.example.lesson_8_lukin.presentation.mainWindow.mainWindowFragment
import com.example.lesson_8_lukin.presentation.addNoteWindow.addNoteWindowFragment

class MainActivity : AppCompatActivity(), FragmentListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentContainerView, mainWindowFragment())
            .commit()
    }

    override fun backClick() {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentContainerView, mainWindowFragment())
            .commit()
    }

    override fun newNoteClick() {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentContainerView, addNoteWindowFragment())
            .commit()
    }

    override fun noteClick(note: NoteEntity) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentContainerView, addNoteWindowFragment.newInstance(note))
            .commit()
    }
}