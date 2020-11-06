package com.itis.ganiev.baseproject

import android.os.Bundle
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
            val cap = "Capital: $capital"
            tv_capital.text = cap
        }
        itemView.setOnClickListener {
            //itemClick.invoke(adapterPosition)
        }
        button_delete.setOnClickListener {
            itemClick.invoke(adapterPosition)
        }
    }

    fun updateFromBundle(bundle : Bundle?) {
        if (bundle?.containsKey("KEY_NAME") == true) {
            bundle.getString("KEY_NAME").also {
                tv_country_name.text = it
            }
        }
        if (bundle?.containsKey("KEY_CAPITAL") == true) {
            bundle.getString("KEY_CAPITAL").also {
                tv_capital.text = it
            }
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