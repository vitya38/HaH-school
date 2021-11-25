package com.example.lesson_9_lukin.data.services

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import com.example.lesson_9_lukin.data.ServiceCallbacks
import com.example.lesson_9_lukin.data.weather.Weather
import com.example.lesson_9_lukin.data.remoute.WeatherApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit


class WeatherService : Service() {


    private lateinit var job: Job
    private lateinit var weather: Weather
    private val coroutine = CoroutineScope(Dispatchers.IO)
    private var serviceCallbacks: ServiceCallbacks? = null

    override fun onCreate() {
        super.onCreate()
        getWeatherEveryMinute()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return START_STICKY
    }

    override fun onBind(intent: Intent?): IBinder {
        return MyBinder()
    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }

    private fun getWeatherEveryMinute() {
        job = coroutine.launch {
            while (true) {
                weather = WeatherApi.apiService.getWeather()
                serviceCallbacks?.setWeather(weather)
                TimeUnit.MINUTES.sleep(1)
            }
        }
        stopSelf()
    }

    fun setCallbacks(callbacks: ServiceCallbacks) {
        serviceCallbacks = callbacks
    }

    internal inner class MyBinder : Binder() {
        val service: WeatherService
            get() = this@WeatherService
    }
}