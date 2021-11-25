package com.example.lesson_9_lukin.data.remoute

import com.example.lesson_9_lukin.data.weather.Weather
import retrofit2.http.GET

interface WeatherApiService {
    @GET("weather?q=saransk&units=metric&appid=a924f0f5b30839d1ecb95f0a6416a0c2")
    suspend fun getWeather(): Weather
}