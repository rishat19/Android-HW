package com.itis.ganiev.baseproject

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import kotlinx.android.synthetic.main.fragment_2.*

class Fragment2 : Fragment() {

    var adapter : CountryAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_2, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = CountryAdapter(CountriesRepository.getCountries().toList()) {
            CountriesRepository.removeCountry(it)
            adapter?.updateDataSource(CountriesRepository.getCountries().toList())
        }
        rv_countries.adapter = adapter
        rv_countries.addItemDecoration(DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL))
        val callback = CountryItemTouchHelper(adapter as ItemTouchHelperAdapter)
        val touchHelper = ItemTouchHelper(callback)
        touchHelper.attachToRecyclerView(rv_countries)
        fab.setOnClickListener {
            val dialog = DialogWindow()
            val listener : (String, String, Int) -> Unit = { name, capital, position ->
                val country = Country(name, capital)
                if (position > CountriesRepository.getSize() || position < 0) {
                    CountriesRepository.addCountry(country)
                } else {
                    CountriesRepository.addCountry(country, position)
                }
                adapter?.updateDataSource(CountriesRepository.getCountries().toList())

            }
            dialog.listener = listener
            dialog.show(requireFragmentManager(), "dialog")
        }

    }

    companion object {
        @JvmStatic
        fun newInstance() =
            Fragment2().apply {
                arguments = Bundle()
            }
    }
}