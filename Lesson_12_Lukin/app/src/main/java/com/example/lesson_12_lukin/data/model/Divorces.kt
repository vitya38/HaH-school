package com.example.lesson_12_lukin.data.model

import com.google.gson.annotations.SerializedName

data class Divorces(
    @SerializedName("start") val start: String?,
    @SerializedName("end") val end: String?,
)