package com.itis.ganiev.baseproject

import android.os.Bundle
import androidx.recyclerview.widget.DiffUtil

class CountryDiffCallback(
    private val oldList:List<Country>,
    private val newList:List<Country>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition].name == newList[newItemPosition].name

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition] == newList[newItemPosition]

    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
        val bundle = Bundle().apply {
            if (oldList[oldItemPosition].name != newList[newItemPosition].name) {
                putString("KEY_NAME", newList[newItemPosition].name)
            }
            if(oldList[oldItemPosition].capital != newList[newItemPosition].capital) {
                putString("KEY_CAPITAL", newList[newItemPosition].capital)
            }
        }
        return if (bundle.isEmpty) null else bundle
    }

}