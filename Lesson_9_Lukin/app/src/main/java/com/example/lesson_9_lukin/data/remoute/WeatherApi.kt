package com.example.lesson_9_lukin.data.remoute

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object WeatherApi {
    private const val URL = "http://api.openweathermap.org/data/2.5/"
    val apiService: WeatherApiService = getRetrofit().create(WeatherApiService::class.java)
    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}