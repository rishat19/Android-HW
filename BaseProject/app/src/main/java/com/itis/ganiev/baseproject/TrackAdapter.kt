package com.itis.ganiev.baseproject

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class TrackAdapter (
    private val list: List<Track>,
    private val itemClick: (Int) -> Unit
) : RecyclerView.Adapter<TrackHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrackHolder =
        TrackHolder.create(parent, itemClick)

    override fun onBindViewHolder(holder: TrackHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

}