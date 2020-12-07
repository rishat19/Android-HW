package com.itis.ganiev.baseproject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.track_list_item.*

class TrackHolder (
    override val containerView: View,
    private val itemClick: (Int) -> Unit
) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun bind(track: Track) {
        with (track) {
            tv_track_author.text = author
            tv_track_title.text = title
            iv_cover.setImageResource(cover)
        }
        itemView.setOnClickListener {
            itemClick(track.id)
        }
    }

    companion object {
        fun create(parent: ViewGroup, itemClick: (Int) -> Unit): TrackHolder =
            TrackHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.track_list_item, parent, false),
                itemClick
            )
    }

}