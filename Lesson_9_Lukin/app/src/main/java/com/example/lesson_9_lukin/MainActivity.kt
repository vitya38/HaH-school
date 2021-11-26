package com.example.lesson_9_lukin

import android.content.BroadcastReceiver
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.ServiceConnection
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.lesson_9_lukin.data.services.ImageService
import com.example.lesson_9_lukin.data.weather.Weather
import com.example.lesson_9_lukin.data.services.WeatherService
import com.example.lesson_9_lukin.data.services.WeatherService.MyBinder
import com.example.lesson_9_lukin.data.ServiceCallbacks
import com.example.lesson_9_lukin.databinding.ActivityMainBinding
import java.lang.StringBuilder

class MainActivity : AppCompatActivity(), ServiceCallbacks {

    companion object {
        const val DOWNLOAD = "download"
        const val IMAGE = "image"
        const val uri = "https://drive.google.com/uc?export=download&id=1AiadE2GQI_IWqcHNsR_i9hVTCz__zNMr"
    }

    private val binding by viewBinding(ActivityMainBinding::bind)

    private var progressBroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            val progress = intent?.getIntExtra("progress", 0)
            if (progress != null) {
                binding.progressBar.progress = progress
            }
        }
    }

    private var imageBroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            val image = intent?.getStringExtra("image")
            binding.imageView.setImageDrawable(Drawable.createFromPath(image))
        }
    }

    private val serviceIntent by lazy { Intent(this, WeatherService::class.java) }
    private var myBinder: MyBinder? = null
    private var bound = false
    private val sConn = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            bound = true
            myBinder = service as MyBinder
            myBinder?.service?.setCallbacks(this@MainActivity)
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            bound = false
            myBinder = null
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bindService(serviceIntent, sConn, BIND_AUTO_CREATE)

        binding.button.setOnClickListener {
            startService(ImageService.createStartIntent(this, uri))
        }
        registerReceiver(progressBroadcastReceiver, IntentFilter(DOWNLOAD))
        registerReceiver(imageBroadcastReceiver, IntentFilter(IMAGE))
    }

    override fun onStop() {
        super.onStop()
        if (bound) {
            unbindService(sConn)
            bound = false
        }
    }

    override fun setWeather(weather: Weather?) {
        if (weather != null) {
            setData(weather)
        }
    }

    private fun setData(weather: Weather) {
        Log.d("TAG", "update")
        runOnUiThread {
            val builder = StringBuilder()
            builder.append(getString(R.string.temp))
            builder.append(" ")
            builder.append(weather.main?.temp)
            val temp = builder.toString()
            builder.clear()
            builder.append(getString(R.string.feels_like))
            builder.append(" ")
            builder.append(weather.main?.feelsLike)
            val feelsLike = builder.toString()
            builder.clear()
            builder.append(getString(R.string.temp_min))
            builder.append(" ")
            builder.append(weather.main?.tempMin)
            val tempMin = builder.toString()
            builder.clear()
            builder.append(getString(R.string.temp_max))
            builder.append(" ")
            builder.append(weather.main?.tempMax)
            val tempMax = builder.toString()
            binding.temp.text = temp
            binding.feelsLike.text = feelsLike
            binding.tempMin.text = tempMin
            binding.tempMax.text = tempMax
        }
    }
}