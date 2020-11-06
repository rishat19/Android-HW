package com.itis.ganiev.baseproject

import java.util.*

object CardsRepository {

    var cards: LinkedList<Card> = LinkedList()

    init {
        cards.add(Card("Finland",
            listOf(
                R.drawable.image_finland_1,
                R.drawable.image_finland_2,
                R.drawable.image_finland_3
            ), "Helsinki"))
        cards.add(Card("France",
            listOf(
                R.drawable.image_france_1,
                R.drawable.image_france_2,
                R.drawable.image_france_3,
                R.drawable.image_france_4
            ), "Paris"))
        cards.add(Card("Italy",
            listOf(
                R.drawable.image_italy_1,
                R.drawable.image_italy_2,
                R.drawable.image_italy_3
            ), "Rome"))
    }

    fun getCards(): Collection<Card> = cards

}