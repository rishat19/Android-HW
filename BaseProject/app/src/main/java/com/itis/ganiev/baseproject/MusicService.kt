package com.itis.ganiev.baseproject

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.Binder
import android.os.IBinder

class MusicService : Service() {

    private lateinit var player: MediaPlayer
    private lateinit var musicBinder: MusicBinder
    private lateinit var trackList: List<Track>
    var currentTrackId: Int? = null
    private lateinit var notification: MusicNotification

    inner class MusicBinder : Binder() {
        fun getService(): MusicService = this@MusicService
    }

    override fun onBind(intent: Intent): IBinder = musicBinder

    override fun onCreate() {
        super.onCreate()
        player = MediaPlayer()
        musicBinder = MusicBinder()
        trackList = TrackRepository.trackList
        currentTrackId = 0
        notification = MusicNotification(this).apply {
            build(2)
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when (intent?.action) {
            "PREVIOUS" -> {
                playPreviousTrack()
            }
            "RESUME" -> {
                if (player.isPlaying) pauseTrack() else playTrack()
            }
            "NEXT" -> {
                playNextTrack()
            }
        }
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()
        player.release()
    }

    fun playPreviousTrack() {
        currentTrackId?.let {
            currentTrackId = if (it == 0) {
                trackList.size - 1
            } else {
                it - 1
            }
            setTrack(currentTrackId?: 0)
            playTrack()
        }
    }

    fun playNextTrack() {
        currentTrackId?.let {
            currentTrackId = if (it == trackList.size - 1) {
                0
            } else {
                it + 1
            }
            setTrack(currentTrackId?: 0)
            playTrack()
        }
    }


    fun pauseTrack() {
        player.pause()
    }

    fun playTrack() {
        player.start()
    }

    fun setTrack(id: Int) {
        if (player.isPlaying) {
            player.stop()
        }
        player = MediaPlayer.create(applicationContext, trackList[id].sound)
        currentTrackId = id
        player.run {
            setOnCompletionListener {
                stop()
            }
        }
        notification.build(id)
    }

}
