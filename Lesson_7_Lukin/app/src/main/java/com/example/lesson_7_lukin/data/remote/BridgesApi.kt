package com.example.lesson_7_lukin.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object BridgesApi {
    private const val BASE_URL = "http://gdemost.handh.ru:1235/"
    val apiService: BridgesApiService = getRetrofit().create(BridgesApiService::class.java)
    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}