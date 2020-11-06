package com.itis.ganiev.baseproject

import android.os.Bundle
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

class CountryAdapter(
    private var list: List<Country>,
    private val itemClick: (Int) -> Unit
) : RecyclerView.Adapter<CountryHolder>(), ItemTouchHelperAdapter {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryHolder =
        CountryHolder.create(parent, itemClick)

    override fun onBindViewHolder(holder: CountryHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: CountryHolder, position: Int, payloads: MutableList<Any>) {
        if (payloads.isEmpty()) {
            super.onBindViewHolder(holder, position, payloads)
        } else {
            (payloads[0] as Bundle).also {
                holder.updateFromBundle(it)
            }
        }
    }

    fun updateDataSource(newList: List<Country>) {
        val callback = CountryDiffCallback(list, newList)
        val diffResult = DiffUtil.calculateDiff(callback, true)
        diffResult.dispatchUpdatesTo(this)
        list = mutableListOf<Country>().apply {
            addAll(newList)
        }
    }

    override fun onItemSwiped(position: Int) {
        itemClick.invoke(position)
    }

}