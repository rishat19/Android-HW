package com.itis.ganiev.baseproject

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class CountryAdapter(
    private val list: List<Country>,
    private val itemClick: (Int) -> Unit
) : RecyclerView.Adapter<CountryHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryHolder =
        CountryHolder.create(parent, itemClick)

    override fun onBindViewHolder(holder: CountryHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

}