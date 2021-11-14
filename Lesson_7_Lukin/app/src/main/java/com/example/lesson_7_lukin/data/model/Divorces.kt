package com.example.lesson_7_lukin.data.model

import com.google.gson.annotations.SerializedName

data class Divorces(
    @SerializedName("start") val start: String?,
    @SerializedName("end") val end: String?,
)