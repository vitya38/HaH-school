package com.example.lesson_9_lukin.data.services

import android.app.Notification
import android.app.Service
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.example.lesson_9_lukin.MainActivity
import com.example.lesson_9_lukin.R
import java.io.BufferedInputStream
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.net.URL
import java.util.zip.ZipInputStream

class ImageService : Service() {

    companion object {
        const val URI = "uri"
        fun createStartIntent(context: Context, uri: String): Intent {
            return Intent(context, ImageService::class.java).putExtra(URI, uri)
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val uri = intent?.getStringExtra(URI)
        startForeground(10, createNotification())
        Thread {
            download(uri)
            val image = unZip()
            val imageIntent = Intent(MainActivity.IMAGE)
            imageIntent.putExtra("image", image)
            sendBroadcast(imageIntent)
            stopSelf()
        }.start()
        return START_REDELIVER_INTENT
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    private fun createNotification(): Notification {
        val builder: NotificationCompat.Builder = NotificationCompat.Builder(this, "chanelId")
        builder.setWhen(System.currentTimeMillis())
        builder.setSmallIcon(R.drawable.ic_launcher_foreground)
        builder.color = Color.RED
        builder.setContentTitle("Загрузка")
        builder.setOngoing(true)
        builder.priority = NotificationCompat.PRIORITY_DEFAULT
        builder.setCategory(NotificationCompat.EXTRA_PROGRESS)
        return builder.build()
    }

    private fun download(uri: String?) {
        val url = URL(uri)
        val connection = url.openConnection()
        connection.connect()
        val fileLength = connection.contentLength
        val input = BufferedInputStream(connection.getInputStream())
        val file = File(filesDir, "myarchive.zip")
        val output = FileOutputStream(file)
        val data = ByteArray(1024)
        var total = 0
        var count = input.read(data)
        while (count != -1) {
            total += count
            val intent = Intent(MainActivity.DOWNLOAD)
            intent.putExtra("progress", (total * 100 / fileLength))
            sendBroadcast(intent)
            output.write(data, 0, count)
            count = input.read(data)
        }
        output.flush()
        output.close()
        input.close()
        val intent = Intent(MainActivity.DOWNLOAD)
        intent.putExtra("progress", 100)
        sendBroadcast(intent)
    }

    private fun unZip(): String {
        val file = File(filesDir, "myarchive.zip")
        val zin = ZipInputStream(FileInputStream(file))
        val zipEntry = zin.nextEntry
        val name = zipEntry.name
        val imageFile = File(filesDir, name)
        val fileOutputStream = FileOutputStream(imageFile)
        var c = zin.read()
        while (c != -1) {
            fileOutputStream.write(c)
            c = zin.read()
        }
        fileOutputStream.flush()
        zin.closeEntry()
        fileOutputStream.close()
        return imageFile.absolutePath
    }

}