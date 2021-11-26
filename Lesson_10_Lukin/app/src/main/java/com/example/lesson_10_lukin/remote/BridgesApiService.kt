package com.example.lesson_10_lukin.remote

import com.example.lesson_10_lukin.model.Bridge
import retrofit2.http.GET

interface BridgesApiService {
    @GET("bridges")
    suspend fun getBridges(): List<Bridge>
}