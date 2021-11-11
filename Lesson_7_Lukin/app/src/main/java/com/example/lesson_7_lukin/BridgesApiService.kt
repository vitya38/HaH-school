package com.example.lesson_7_lukin

import retrofit2.http.GET

interface BridgesApiService {
    @GET("bridges")
    suspend fun getBridges(): List<Bridge>
}