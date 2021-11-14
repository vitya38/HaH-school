package com.example.lesson_7_lukin.data.remote

import com.example.lesson_7_lukin.data.model.Bridge
import retrofit2.http.GET

interface BridgesApiService {
    @GET("bridges")
    suspend fun getBridges(): List<Bridge>
}