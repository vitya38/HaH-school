package com.example.lesson_10_lukin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.example.lesson_10_lukin.databinding.ActivityMapsBinding
import android.Manifest
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.example.lesson_10_lukin.model.Bridge
import com.example.lesson_10_lukin.model.Divorces
import com.example.lesson_10_lukin.remote.BridgesApi
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Locale
import kotlin.math.abs

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private var map: GoogleMap? = null
    private lateinit var binding: ActivityMapsBinding

    private val locationPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
        if (isGranted) {
            enableLocation()
        } else {
            if (!ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_COARSE_LOCATION)) {
                Toast.makeText(this, "Нужны разрешения", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }


    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        googleMap.uiSettings.apply {
            isMapToolbarEnabled = false
        }
        enableLocation()
        val piter = LatLng(59.942499, 30.353021)
        googleMap.addMarker(MarkerOptions().position(piter).title("Marker in Piter"))
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(piter, 12.3F))
        loadBridges(googleMap)
        googleMap.setOnMapClickListener {
            binding.included.card.isVisible = false
        }
    }

    private fun loadBridges(googleMap: GoogleMap) {
        setStateLoading()
        var bridges: List<Bridge> = emptyList()
        lifecycleScope.launch {
            try {
                bridges = BridgesApi.apiService.getBridges()
                setBridges(bridges, googleMap)
            } catch (e: java.lang.Exception) {
                setStateError(e, googleMap)
            }
        }
        googleMap.setOnMarkerClickListener { marker ->
            if (marker.tag != null) {
                for (i in 0 until bridges.count()) {
                    if (bridges[i].name == marker.tag) {
                        val time = printDivorces(bridges[i].divorces)
                        val icon = time(bridges[i].divorces)
                        val name = bridges[i].name
                        binding.included.textViewBridgeName.text = name
                        binding.included.textViewTime.text = time
                        binding.included.imageViewBridge.setImageResource(icon)
                        binding.included.card.isVisible = true
                    }
                }
            }
            true
        }
    }

    private fun setBridges(bridges: List<Bridge>, map: GoogleMap?) {
        bridges.forEach { bridge ->
            val icon = time(bridge.divorces)
            val name = bridge.name

            val lat = bridge.lat
            val lng = bridge.lng
            val latlng = LatLng(lat!!, lng!!)
            val marker = map?.addMarker(MarkerOptions().position(latlng).icon(BitmapDescriptorFactory.fromResource(icon)))
            marker?.tag = name
        }
        binding.progressBar.isVisible = false
    }

    private fun setStateLoading() {
        binding.progressBar.isVisible = true
    }

    private fun setStateError(e: Exception, googleMap: GoogleMap) {
        binding.progressBar.isVisible = false
        val dialogAlert = AlertDialog.Builder(this, R.style.button)
        dialogAlert.setTitle(R.string.something_goes_wrong)
        dialogAlert.setMessage(e.toString())
        dialogAlert.setPositiveButton(
            R.string.positive_button
        ) { dialog, _ ->
            loadBridges(googleMap)
            dialog.dismiss()
        }
        val alert = dialogAlert.create()
        alert.show()
    }

    private fun enableLocation() {
        when {
            ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED -> {
                map?.isMyLocationEnabled = true
            }
            else -> {
                locationPermissionLauncher.launch(Manifest.permission.ACCESS_COARSE_LOCATION)
            }
        }
    }

    private fun time(times: List<Divorces>?): Int {
        val hour = 3600000
        val formaterDay = SimpleDateFormat("yyyy.MM.dd", Locale.getDefault())
        val format = SimpleDateFormat("yyyy.MM.dd HH:mm", Locale.getDefault())
        val now = System.currentTimeMillis()
        val today = formaterDay.format(now)
        var result = R.drawable.ic_brige_normal
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
                return R.drawable.ic_brige_late
            } else if (abs(timeFromListStart - now) <= hour) {
                result = R.drawable.ic_brige_soon
            }
        }
        return result
    }

    private fun printDivorces(divorces: List<Divorces>?): String {
        val builder = StringBuilder()
        divorces?.forEach { item ->
            builder.append(item.start)
            builder.append('—')
            builder.append(item.end)
            builder.append("     ")
        }
        return builder.toString()
    }

}