package com.itis.ganiev.baseproject.presentation.views

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
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
import com.itis.ganiev.baseproject.R
import com.itis.ganiev.baseproject.data.database.entities.City
import com.itis.ganiev.baseproject.domain.FindCityUseCase
import com.itis.ganiev.baseproject.domain.LocationUseCase
import com.itis.ganiev.baseproject.presentation.presenters.SearchPresenter
import com.itis.ganiev.baseproject.presentation.recyclerview.WeatherAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.coroutines.launch
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import javax.inject.Inject

@AndroidEntryPoint
class SearchFragment : MvpAppCompatFragment(),
    com.itis.ganiev.baseproject.presentation.views.SearchView {

    @Inject
    lateinit var findCityUseCase: FindCityUseCase

    @Inject
    lateinit var locationUseCase: LocationUseCase

    @InjectPresenter
    lateinit var presenter: SearchPresenter

    @ProvidePresenter
    fun providePresenter() =
        SearchPresenter(findCityUseCase, locationUseCase, requireContext().applicationContext)

    override fun showCityList(latitude: Double, longitude: Double) {
        initRecyclerView(latitude, longitude)
    }

    override fun checkLocationPermission() {
        presenter.onCheckPermissionResult(
            ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        )
    }

    override fun requestPermissions() {
        requestPermissions(
            arrayOf(
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION
            ), PERMISSION_GEO
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSION_GEO) {
            grantResults.forEach {
                var areGranted = true
                if (it == PackageManager.PERMISSION_DENIED) {
                    areGranted = false
                }
                if (areGranted) {
                    presenter.checkLocationPermission()
                } else {
                    presenter.showDefaultCities()
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    private fun initRecyclerView(latitude: Double, longitude: Double) {
        rv_city_list.run {
            lifecycleScope.launch {
                adapter = WeatherAdapter(
                    presenter.getCityList(latitude, longitude)
                ) { cityName ->
                    fragmentManager
                        ?.beginTransaction()
                        ?.replace(R.id.fragment_container, InfoFragment.newInstance(cityName))
                        ?.addToBackStack("list")
                        ?.commit()
                }
            }
            rv_city_list.layoutManager = LinearLayoutManager(this.context)
            rv_city_list.addItemDecoration(
                DividerItemDecoration(
                    this.context,
                    DividerItemDecoration.VERTICAL
                )
            )
        }

    }

    private fun initSearchView() {
        search_view.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                lifecycleScope.launch {
                    try {
                        fragmentManager
                            ?.beginTransaction()
                            ?.replace(R.id.fragment_container, InfoFragment.newInstance(query))
                            ?.addToBackStack("list")
                            ?.commit()
                    } catch (ex: Exception) {
                        Toast.makeText(
                            context,
                            "Наверно указан населённый пункт",
                            Toast.LENGTH_SHORT
                        ).show()
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
        private const val PERMISSION_GEO = 999
        fun newInstance() =
            SearchFragment().apply {
                arguments = Bundle()
            }
    }

}
