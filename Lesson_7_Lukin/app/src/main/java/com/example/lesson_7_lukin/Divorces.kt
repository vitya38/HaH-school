package com.example.lesson_7_lukin

import com.google.gson.annotations.SerializedName

data class Divorces(
    @SerializedName("start") val start: String?,
    @SerializedName("end") val end: String?,
)