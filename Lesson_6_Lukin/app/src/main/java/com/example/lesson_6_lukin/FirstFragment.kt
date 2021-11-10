package com.example.lesson_6_lukin

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.lesson_6_lukin.R
import com.google.android.material.appbar.MaterialToolbar

class FirstFragment : Fragment(R.layout.fragment_first) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val toolbar = view.findViewById<MaterialToolbar>(R.id.toolbar1)
        toolbar.menu.findItem(R.id.loupe).setOnMenuItemClickListener {
            Toast.makeText(this.context, it.title.toString(), Toast.LENGTH_SHORT).show()
            true
        }
        toolbar.menu.findItem(R.id.subMenu1).setOnMenuItemClickListener {
            Toast.makeText(this.context, it.title.toString(), Toast.LENGTH_SHORT).show()
            true
        }
        toolbar.menu.findItem(R.id.subMenu2).setOnMenuItemClickListener {
            Toast.makeText(this.context, it.title.toString(), Toast.LENGTH_SHORT).show()
            true
        }
        toolbar.menu.findItem(R.id.subMenu3).setOnMenuItemClickListener {
            Toast.makeText(this.context, it.title.toString(), Toast.LENGTH_SHORT).show()
            true
        }
    }
}