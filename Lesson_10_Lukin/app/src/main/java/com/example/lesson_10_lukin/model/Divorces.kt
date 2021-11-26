package com.example.lesson_10_lukin.model

import com.google.gson.annotations.SerializedName

data class Divorces(
    @SerializedName("start") val start: String?,
    @SerializedName("end") val end: String?,
)