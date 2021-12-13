package com.example.lesson_12_lukin.data.model

import java.lang.Exception

sealed class BridgesListState {
    object Loading : BridgesListState()
    class Data(val bridges: List<Bridge>) : BridgesListState()
    class Error(val exception: Exception) : BridgesListState()
}