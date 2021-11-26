package com.example.lesson_9_lukin.data

import com.example.lesson_9_lukin.data.weather.Weather

interface ServiceCallbacks {
    fun setWeather(weather: Weather?)
}