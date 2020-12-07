package com.itis.ganiev.baseproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_track_list.*

class TrackListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_track_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rv_track_list.adapter = TrackAdapter(
            TrackRepository.trackList
        ) { id: Int ->
            var args = Bundle().also {
                it.putInt("ID", id)
            }
            fragmentManager?.beginTransaction()
                ?.replace(R.id.fragment_container, TrackInfoFragment().apply {
                    arguments = args
                })
                ?.commit()
        }
        rv_track_list.layoutManager = LinearLayoutManager(this.context)
        rv_track_list.addItemDecoration(DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL))
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            TrackListFragment().apply {
                arguments = Bundle()
            }
    }

}