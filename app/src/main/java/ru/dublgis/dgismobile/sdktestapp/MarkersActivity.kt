package ru.dublgis.dgismobile.sdktestapp

import android.widget.Toast
import ru.dublgis.dgismobile.mapsdk.*
import java.lang.ref.WeakReference

class MarkersActivity : MapActivity() {

    private var marker: Marker? = null

    override fun onDGisMapReady() {
        map?.setOnClickListener(this::onMapClicked)
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
                        map.setSelectedObjects(listOf())
                        marker = null
                    }
                }
            }
        }
    }
}
