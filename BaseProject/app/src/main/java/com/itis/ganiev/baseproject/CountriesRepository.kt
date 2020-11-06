package com.itis.ganiev.baseproject

import java.util.*

object CountriesRepository {

    var countries: LinkedList<Country> = LinkedList()

    init {
        countries.add(Country("Austria", "Vienna"))
        countries.add(Country("Belarus", "Minsk"))
        countries.add(Country("Belgium", "Brussels"))
        countries.add(Country("Denmark", "Copenhagen"))
        countries.add(Country("Finland", "Helsinki"))
        countries.add(Country("France", "Paris"))
        countries.add(Country("Germany", "Berlin"))
        countries.add(Country("Greece", "Athens"))
        countries.add(Country("Iceland", "Reykjavík"))
        countries.add(Country("Ireland", "Dublin"))
        countries.add(Country("Italy", "Rome"))
    }

    fun addCountry(country: Country) {
        countries.addLast(country)
    }

    fun addCountry(country: Country, index: Int) {
        countries.add(index, country)
    }

    fun getCountries(): Collection<Country> = countries

    fun getSize(): Int = countries.size

    fun removeCountry(id: Int) {
        countries.removeAt(id)
    }

}