package com.itis.ganiev.baseproject

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

class TrackInfoFragment : Fragment() {

    private lateinit var track: Track

    private lateinit var authorTextView: TextView
    private lateinit var titleTextView: TextView
    private lateinit var coverImageView: ImageView
    private lateinit var previousButton: ImageView
    private lateinit var nextButton: ImageView
    private lateinit var playButton: ImageView
    private lateinit var pauseButton: ImageView

    private var musicService: MusicService? = null

    private val binderConnection = object : ServiceConnection {
        override fun onServiceConnected(className: ComponentName, service: IBinder) {
            musicService = (service as? MusicService.MusicBinder)?.getService()
            if (musicService != null) {
                val id = arguments?.getInt("ID")
                id?.let {
                    track = TrackRepository.trackList[id]
                    authorTextView.text = track.author
                    titleTextView.text = track.title
                    coverImageView.setImageResource(track.cover)
                    musicService?.setTrack(id)
                    musicService?.playTrack()
                    previousButton.setOnClickListener {
                        musicService?.playPreviousTrack()
                        updateView(musicService?.currentTrackId?:0)
                    }
                    nextButton.setOnClickListener {
                        musicService?.playNextTrack()
                        updateView(musicService?.currentTrackId?:0)
                    }
                    playButton.setOnClickListener {
                        musicService?.playTrack()
                        playButton.visibility = View.GONE
                        pauseButton.visibility = View.VISIBLE
                    }
                    pauseButton.setOnClickListener {
                        musicService?.pauseTrack()
                        playButton.visibility = View.VISIBLE
                        pauseButton.visibility = View.GONE
                    }
                }
            }
        }
        override fun onServiceDisconnected(className: ComponentName) {
            musicService = null
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_track_info, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            TrackInfoFragment().apply {
                arguments = Bundle()
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        authorTextView = view.findViewById(R.id.tv_track_author)
        titleTextView = view.findViewById(R.id.tv_track_title)
        coverImageView = view.findViewById(R.id.iv_cover)
        previousButton = view.findViewById(R.id.iv_navigation_previous)
        nextButton = view.findViewById(R.id.iv_navigation_next)
        playButton = view.findViewById(R.id.iv_navigation_play)
        pauseButton = view.findViewById(R.id.iv_navigation_pause)
    }

    override fun onResume() {
        super.onResume()
        val intent = Intent(this.context, MusicService::class.java)
        activity?.bindService(intent, binderConnection, Context.BIND_AUTO_CREATE)
    }

    private fun updateView(id: Int) {
        id.let {
            track = TrackRepository.trackList[id]
            authorTextView.text = track.author
            titleTextView.text = track.title
            coverImageView.setImageResource(track.cover)
            playButton.visibility = View.GONE
            pauseButton.visibility = View.VISIBLE
            playButton.setOnClickListener {
                musicService?.playTrack()
                playButton.visibility = View.GONE
                pauseButton.visibility = View.VISIBLE
            }
        }
    }

}