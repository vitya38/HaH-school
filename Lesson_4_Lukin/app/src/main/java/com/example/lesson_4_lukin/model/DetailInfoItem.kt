package com.example.lesson_4_lukin.model

open class DetailInfoItem(
    icon: Int,
    cardName: String,
    val cardText: String,
    val isRedText: Boolean
) : BaseInfoItem(icon, cardName)
