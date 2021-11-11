package com.example.lesson_7_lukin

import com.google.gson.annotations.SerializedName

data class Bridge(
    @SerializedName("id") val id: String?,
    @SerializedName("name") val name: String?,
    @SerializedName("name_eng") val nameEng: String?,
    @SerializedName("description") val description: String?,
    @SerializedName("description_eng") val descriptionEng: String?,
    @SerializedName("divorces") val divorces: List<Divorces>?,
    @SerializedName("lat") val lat: String?,
    @SerializedName("lng") val lng: String?,
    @SerializedName("photo_close_url") val photoCloseUrl: String?,
    @SerializedName("photo_open_url") val photoOpenUrl: String?,
    @SerializedName("public") val public: Boolean?,
)