package com.itis.ganiev.baseproject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.viewpagerindicator.TitlePageIndicator
import kotlinx.android.synthetic.main.item_card.view.*

class CardAdapter(
    var cards:List<Card>
) : RecyclerView.Adapter<CardAdapter.CardHolder>() {

    inner class CardHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(card: Card) {
            with (card) {
                itemView.view_pager.adapter = CardPagerAdapter(images)
                itemView.card_title.text = title
                val capital = "Capital: $description"
                itemView.card_description.text = capital
            }
            val titleIndicator: TitlePageIndicator = itemView.findViewById(R.id.indicator) as TitlePageIndicator
            titleIndicator.setViewPager(itemView.view_pager)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardHolder =
        CardHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_card, parent, false))

    override fun onBindViewHolder(holder: CardHolder, position: Int) {
        holder.bind(cards[position])
    }

    override fun getItemCount(): Int = cards.size

}