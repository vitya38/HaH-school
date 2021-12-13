package com.example.lesson_12_lukin.data.repository

import com.example.lesson_12_lukin.data.model.Bridge
import com.example.lesson_12_lukin.data.remote.BridgeApiService
import javax.inject.Inject

class BridgesRepository @Inject constructor(
    private val apiService: BridgeApiService,
) {
    suspend fun getBridges(): List<Bridge> {
        return apiService.getBridges()
    }

    suspend fun getBridge(id: String): Bridge {
        return apiService.getBridge(id)
    }
}