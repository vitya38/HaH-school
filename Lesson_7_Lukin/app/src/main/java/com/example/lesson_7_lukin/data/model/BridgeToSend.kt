package com.example.lesson_7_lukin.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
class BridgeToSend(
    val name: String?,
    val closeTime: String?,
    val description: String?,
    val picture: String?,
    val icon: Int?,
) : Parcelable