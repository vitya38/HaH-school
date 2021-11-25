package com.example.lesson_9_lukin.data.weather

import com.google.gson.annotations.SerializedName

data class Weather(
    @SerializedName("main") val main: Main?,
)