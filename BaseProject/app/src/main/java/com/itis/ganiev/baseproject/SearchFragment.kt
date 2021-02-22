package com.itis.ganiev.baseproject

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.coroutines.launch

class SearchFragment : Fragment() {

    private val api = ApiFactory.weatherApi
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient

    private val DEFAULT_LATITUDE = 55.7887
    private val DEFAULT_LONGITUDE = 49.1221

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        context?.also {
            fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(it)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (context?.let {
                ActivityCompat.checkSelfPermission(
                    it,
                    Manifest.permission.ACCESS_FINE_LOCATION
                )
            } != PackageManager.PERMISSION_GRANTED && context?.let {
                ActivityCompat.checkSelfPermission(
                    it,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                )
            } != PackageManager.PERMISSION_GRANTED
        ) {
            initRecyclerView(DEFAULT_LATITUDE, DEFAULT_LONGITUDE)
        }
        fusedLocationProviderClient.lastLocation.addOnSuccessListener { location ->
            if (location != null) {
                initRecyclerView(location.latitude, location.longitude)
            } else {
                Toast.makeText(this.context,"Наверно указан населённый пункт", Toast.LENGTH_SHORT).show()
                initRecyclerView(DEFAULT_LATITUDE, DEFAULT_LONGITUDE)
            }
        }
        initSearchView()
    }

    private fun initRecyclerView(latitude: Double, longitude: Double) {
        rv_city_list.run {
            lifecycleScope.launch {
                adapter = WeatherAdapter(
                    api.getWeatherList(latitude, longitude,20)
                ) { cityName ->
                    fragmentManager
                        ?.beginTransaction()
                        ?.replace(R.id.fragment_container, InfoFragment.newInstance(cityName))
                        ?.addToBackStack("list")
                        ?.commit()
                }
            }
            rv_city_list.layoutManager = LinearLayoutManager(this.context)
            rv_city_list.addItemDecoration(DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL))
        }

    }

    private fun initSearchView() {
        search_view.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                lifecycleScope.launch {
                    try {
                        fragmentManager
                            ?.beginTransaction()
                            ?.replace(R.id.fragment_container, InfoFragment.newInstance(query))
                            ?.addToBackStack("list")
                            ?.commit()
                    } catch (ex: Exception) {
                        Toast.makeText(context,"Наверно указан населённый пункт", Toast.LENGTH_SHORT).show()
                    }

                }
                return false
            }
            override fun onQueryTextChange(query: String): Boolean {
                return false
            }
        })
    }

    companion object {
        fun newInstance() =
            SearchFragment().apply {
                arguments = Bundle()
            }
    }

}
