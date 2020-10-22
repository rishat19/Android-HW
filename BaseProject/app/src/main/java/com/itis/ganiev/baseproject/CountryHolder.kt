package com.itis.ganiev.baseproject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_country.*

class CountryHolder(
    override val containerView: View,
    private val itemClick: (Int) -> Unit
) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun bind(country: Country) {
        with (country) {
            tv_country_name.text = name
            iv_flag.setBackgroundResource(flag)
        }
        itemView.setOnClickListener {
            itemClick(country.id)
        }
    }

    companion object {

        fun create(parent: ViewGroup, itemClick: (Int) -> Unit): CountryHolder =
            CountryHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_country, parent, false),
                itemClick
            )

    }

}