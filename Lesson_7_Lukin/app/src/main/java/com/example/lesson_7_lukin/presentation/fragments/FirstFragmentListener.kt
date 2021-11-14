package com.example.lesson_7_lukin.presentation.fragments

import com.example.lesson_7_lukin.data.model.BridgeToSend

interface FirstFragmentListener {
    fun click(bridge: BridgeToSend)
    fun backClick()
}