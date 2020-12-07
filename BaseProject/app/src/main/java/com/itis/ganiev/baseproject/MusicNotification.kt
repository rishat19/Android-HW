package com.itis.ganiev.baseproject

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.media.app.NotificationCompat.MediaStyle as NotificationCompatMediaStyle

class MusicNotification(val context:Context) {

    private val CHANNEL_ID: String = "902"
    private val NOTIFICATION_ID: Int = 401

    private var notificationManager: NotificationManager? = null
    private var notificationBuilder: NotificationCompat.Builder

    init {
        notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channelName = context.getString(R.string.channel_name)
            val channelDescription = context.getString(R.string.channel_description)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = notificationManager?.getNotificationChannel(CHANNEL_ID) ?:  NotificationChannel(CHANNEL_ID, channelName, importance).apply {
                description = channelDescription
            }
            notificationManager?.createNotificationChannel(channel)
        }

        val previousIntent = Intent(context,MusicService::class.java).apply {
            action = "PREVIOUS"
        }
        val resumeIntent = Intent(context,MusicService::class.java).apply {
            action = "RESUME"
        }
        val nextIntent = Intent(context,MusicService::class.java).apply {
            action = "NEXT"
        }
        val previousPendingIntent = PendingIntent.getService(context,0, previousIntent, 0)
        val resumePendingIntent = PendingIntent.getService(context,1, resumeIntent, 0)
        val nextPendingIntent = PendingIntent.getService(context,2, nextIntent, 0)

        notificationBuilder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_baseline_play_arrow)
            .addAction(R.drawable.ic_baseline_skip_previous, "Previous", previousPendingIntent)
            .addAction(R.drawable.ic_baseline_games, "Pause", resumePendingIntent)
            .addAction(R.drawable.ic_baseline_skip_next, "Next", nextPendingIntent )
            .setNotificationSilent()

    }

    fun build (id: Int) {
        val track = TrackRepository.trackList[id]
        val cover = BitmapFactory.decodeResource(context.resources, track.cover)
        val builder = notificationBuilder
            .setContentTitle(track.title)
            .setContentText(track.author)
            .setStyle(NotificationCompatMediaStyle())
            .setLargeIcon(cover)
        val notification: Notification = builder.build()
        notificationManager?.notify(NOTIFICATION_ID, notification)
    }

}