package com.example.lesson_12_lukin.data.remote

import com.example.lesson_12_lukin.data.model.Bridge
import retrofit2.http.GET
import retrofit2.http.Path

interface BridgeApiService {
    @GET("bridges")
    suspend fun getBridges(): List<Bridge>

    @GET("bridges/{id}")
    suspend fun getBridge(@Path("id") id: String): Bridge
}