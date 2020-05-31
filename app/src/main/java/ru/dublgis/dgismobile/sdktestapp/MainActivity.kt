package ru.dublgis.dgismobile.sdktestapp

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.location.*
import ru.dublgis.dgismobile.mapsdk.*
import ru.dublgis.dgismobile.mapsdk.clustering.ClustererStyle
import ru.dublgis.dgismobile.mapsdk.clustering.ClustererOptions
import ru.dublgis.dgismobile.mapsdk.clustering.InputMarker
import java.lang.ref.WeakReference
import ru.dublgis.dgismobile.mapsdk.Map as DGisMap
import ru.dublgis.dgismobile.mapsdk.MapFragment as DGisMapFragment


const val LOCATION_PERMISSION_REQUEST_ID: Int = 777
const val LOC_PERM = Manifest.permission.ACCESS_FINE_LOCATION


class MainActivity : AppCompatActivity() {

    private lateinit var locationProvider: FusedLocationProviderClient
    private var map: DGisMap? = null
    private var location: Location? = null
    private var marker: Marker? = null
    private var inputMarkersList = mutableListOf<InputMarker>()

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        locationProvider = LocationServices.getFusedLocationProviderClient(this)

        val mapFragment = supportFragmentManager.findFragmentById(R.id.mapFragment)
                as DGisMapFragment

        val apiKey = resources.getString(R.string.dgis_map_key)

        mapFragment.mapReadyCallback = this::onDGisMapReady
        mapFragment.setup(
            apiKey = apiKey,
            center = LonLat(55.30771, 25.20314),
            zoom = 12.0
        )

        val grant = ContextCompat.checkSelfPermission(this, LOC_PERM)
        if (grant != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(LOC_PERM),
                LOCATION_PERMISSION_REQUEST_ID
            )
        } else {
            requestLocation()
        }

        mapOf(
            R.id.zoom_in to this::zoomInMap,
            R.id.zoom_out to this::zoomOutMap,
            R.id.location to this::centerMap

        ).forEach {
            val btn = findViewById<ImageButton>(it.key)
            btn.setOnClickListener(it.value)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when (requestCode) {
            LOCATION_PERMISSION_REQUEST_ID -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    requestLocation()
                }
            }
        }
    }

    private fun onDGisMapReady(controller: DGisMap?) {
        map = controller
        map?.setOnClickListener(this::onMapClicked)
    }

    private fun requestLocation() {
        val grant = ContextCompat.checkSelfPermission(this, LOC_PERM)
        if (grant != PackageManager.PERMISSION_GRANTED) {
            return
        }

        val request = LocationRequest.create()
        request.interval = 60000
        request.fastestInterval = 20000
        request.priority = LocationRequest.PRIORITY_HIGH_ACCURACY

        val listener = object : LocationCallback() {
            override fun onLocationResult(res: LocationResult?) {
                res?.lastLocation?.let {
                    this@MainActivity.location = it
                }
            }
        }
        locationProvider.requestLocationUpdates(request, listener, null)
    }

    private fun centerMap(@Suppress("UNUSED_PARAMETER") view: View?) {
        if (map == null || location == null) {
            return
        }

        map?.run {
            location?.let {
                center = LonLat(it.longitude, it.latitude)
                zoom = 16.0
            }
        }
    }

    private fun zoomInMap(@Suppress("UNUSED_PARAMETER") view: View?) {
        map?.run {
            zoom = zoom.inc()
        }
    }

    private fun zoomOutMap(@Suppress("UNUSED_PARAMETER") view: View?) {
        map?.run {
            zoom = zoom.dec()
        }
    }

    private fun onMapClicked(pointer: MapPointerEvent) {
        map?.let { map ->
            pointer.target?.let { mapObject ->
                map.setSelectedObjects(listOf(mapObject))
            }

            if (marker != null) {
                marker?.position = pointer.lngLat
            } else {
                val ctx = WeakReference(this)
                val markerOptions = MarkerOptions(
                    pointer.lngLat,
                    icon = iconFromSvgAsset(assets, "pin.svg"),
                    size = 30.0 to 48.0,
                    anchor = 15.0 to 48.0
                )
                marker = map.addMarker(
                    markerOptions
                )

                marker?.setOnClickListener {
                    ctx.get()?.let { activity ->

                        val fmt = { it: LonLat ->
                            val dp = { it: Double -> "${it.toString().take(10)}" }

                            "${dp(it.lat)}, ${dp(it.lon)}"
                        }

                        val msg = "remove marker\n${marker?.position?.let { fmt(it) }}"

                        Toast.makeText(activity, msg, Toast.LENGTH_LONG)
                            .show()

                        map.removeMarker(marker!!)
                        map.setSelectedObjects(listOf());
                        marker = null
                    }
                }
            }
        }
    }

    //TODO
    private fun showClusterer(clusterMarkers: Collection<InputMarker>) {
        val clusterer = map?.createClusterer(ClustererOptions(ClustererStyle(), 60))
        clusterer?.load(clusterMarkers)
    }
}
