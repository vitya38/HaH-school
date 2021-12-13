package com.example.lesson_12_lukin.data.model

import java.lang.Exception

sealed class BridgeListState {
    object Loading : BridgeListState()
    class Data(val bridge: Bridge) : BridgeListState()
    class Error(val exception: Exception) : BridgeListState()
}