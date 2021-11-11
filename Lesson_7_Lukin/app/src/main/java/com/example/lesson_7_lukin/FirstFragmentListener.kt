package com.example.lesson_7_lukin

interface FirstFragmentListener {
    fun click(icon: Int, picture: String?, name: String?, time: String, description: String?)
    fun backClick()
}