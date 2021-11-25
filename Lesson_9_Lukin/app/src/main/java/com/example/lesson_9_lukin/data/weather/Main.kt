package com.example.lesson_9_lukin.data.weather

import com.google.gson.annotations.SerializedName

data class Main(
    @SerializedName("temp") val temp: String?,
    @SerializedName("feels_like") val feelsLike: String?,
    @SerializedName("temp_min") val tempMin: String?,
    @SerializedName("temp_max") val tempMax: String?,
)