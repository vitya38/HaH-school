package com.example.lesson_12_lukin.presentation.map

import android.os.Bundle
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.example.lesson_12_lukin.R
import com.example.lesson_12_lukin.data.model.Bridge
import com.example.lesson_12_lukin.data.model.BridgesListState
import com.example.lesson_12_lukin.data.model.Divorces
import com.google.android.gms.maps.GoogleMap
import com.example.lesson_12_lukin.databinding.ActivityMapsBinding
import com.example.lesson_12_lukin.presentation.base.BaseActivity
import com.example.lesson_12_lukin.presentation.detail.BridgeDetailFragment
import com.example.lesson_12_lukin.presentation.list.BridgeListFragment
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.Locale
import kotlin.math.abs

class MapsActivity : BaseActivity(), OnMapReadyCallback, BridgeFragmentListener {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding
    private lateinit var mapFragment: SupportMapFragment
    private val viewModel: MapsActivityViewModel by lazy {
        viewModelFactory.create(MapsActivityViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mapFragment = supportFragmentManager
            .findFragmentById(R.id.fragmentContainerView) as SupportMapFragment
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentContainerView, BridgeListFragment())
            .commit()
        lifecycleScope.launchWhenStarted {
            viewModel.loadBridges()
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        googleMap.uiSettings.apply {
            isMapToolbarEnabled = false
        }
        val piter = LatLng(59.942499, 30.353021)
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(piter, 12.3F))
        viewModel.bridgesLiveData.observe(this) { state ->
            when (state) {
                BridgesListState.Loading -> setStateLoading()
                is BridgesListState.Data -> {
                    if (state.bridges.isNotEmpty()) {
                        setStateData(state.bridges, googleMap)
                    } else {
                        setStateEmpty()
                    }
                }
                is BridgesListState.Error -> setStateError(state.exception)
            }
        }
        googleMap.setOnMapClickListener {
            binding.bridgeView.isVisible = false
        }
        binding.toolbar.setNavigationOnClickListener {
            backClick()
        }
    }

    private fun setStateLoading() {
        binding.progressBar.isVisible = true
        binding.textViewSupport.isVisible = false
    }

    private fun setStateData(bridges: List<Bridge>, googleMap: GoogleMap) {
        binding.progressBar.isVisible = false
        binding.textViewSupport.isVisible = false
        bridges.forEach { bridge ->
            val icon = time(bridge.divorces)
            val name = bridge.name
            val lat = bridge.lat?.toDouble()
            val lng = bridge.lng?.toDouble()
            if (lat != null && lng != null) {
                val coordinates = LatLng(lat, lng)
                val marker = googleMap.addMarker(
                    MarkerOptions()
                        .position(coordinates)
                        .icon(BitmapDescriptorFactory.fromResource(icon))
                        .anchor(0.5f, 0.5f)
                )
                marker?.tag = name
            }
        }
        googleMap.setOnMarkerClickListener { marker ->
            if (marker.tag != null) {
                for (i in 0 until bridges.count()) {
                    if (bridges[i].name == marker.tag) {
                        binding.bridgeView.isVisible = true
                        binding.bridgeView.setBridge(bridges[i])
                        break
                    }
                }
            }
            true
        }
    }

    private fun setStateEmpty() {
        binding.progressBar.isVisible = false
        binding.textViewSupport.isVisible = true
        binding.textViewSupport.text = getText(R.string.empty)
    }

    private fun setStateError(e: Exception) {
        binding.progressBar.isVisible = false
        binding.textViewSupport.isVisible = true
        binding.textViewSupport.text = e.toString()
    }

    private fun time(times: List<Divorces>?): Int {
        val hour = 3600000
        val formaterDay = SimpleDateFormat("yyyy.MM.dd", Locale.getDefault())
        val format = SimpleDateFormat("yyyy.MM.dd HH:mm", Locale.getDefault())
        val now = System.currentTimeMillis()
        val today = formaterDay.format(now)
        var result = R.drawable.ic_brige_normal_png
        times?.forEach { divorces ->
            val builder = StringBuilder()
            builder.append(today)
            builder.append(' ')
            builder.append(divorces.start)
            val timeFromListStart = format.parse(builder.toString())?.time
            builder.clear()
            builder.append(today)
            builder.append(' ')
            builder.append(divorces.end)
            val timeFromListEnd = format.parse(builder.toString())?.time
            if (now in timeFromListStart!!..timeFromListEnd!!) {
                return R.drawable.ic_brige_late_png
            } else if (abs(timeFromListStart - now) <= hour) {
                result = R.drawable.ic_brige_soon_png
            }
        }
        return result
    }

    override fun click(id: String) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentContainerView, BridgeDetailFragment.newInstance(id))
            .addToBackStack(null)
            .commit()
        binding.appBar.isVisible = false
    }

    override fun backClick() {
        supportFragmentManager
            .popBackStack()
        binding.appBar.isVisible = false
    }

    override fun mapClick() {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentContainerView, mapFragment)
            .addToBackStack(null)
            .commit()
        mapFragment.getMapAsync(this)
        binding.appBar.isVisible = true
    }
}