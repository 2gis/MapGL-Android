package ru.dublgis.dgismobile.sdktestapp

import android.widget.Toast
import ru.dublgis.dgismobile.mapsdk.LonLat
import ru.dublgis.dgismobile.mapsdk.MapPointerEvent
import ru.dublgis.dgismobile.mapsdk.Marker
import ru.dublgis.dgismobile.mapsdk.MarkerOptions
import ru.dublgis.dgismobile.mapsdk.image.ImageFactory
import ru.dublgis.dgismobile.mapsdk.labels.LabelOptions
import java.io.File
import java.lang.ref.WeakReference


class MarkersActivity : MapActivity() {

    private var marker: Marker? = null

    override fun onDGisMapReady() {
        map?.setOnClickListener(this::onMapClicked)
        showMarkerLabel()
        showMarker()
    }

    private fun showMarker() {
        map?.addMarker(
            MarkerOptions(
                map!!.center,
                size = 30.0 to 48.0,
                anchor = 15.0 to 48.0
            )
        )
    }

    private fun showMarkerLabel() {
        map?.addMarker(
            MarkerOptions(
                LonLat(55.31878, 25.23584),
                label = LabelOptions(text = "The marker's label")
            )
        )
    }

    private fun onMapClicked(pointer: MapPointerEvent) {
        map?.let { map ->
            if (marker != null) {
                marker?.position = pointer.lngLat
            } else {
                val fileName = "$filesDir/icon_adaptive_foreground.png"
                val file = File(fileName)

                val markerOptions = MarkerOptions(
                    pointer.lngLat,
                    icon = ImageFactory(this).fromFile(file),
                    size = 30.0 to 48.0,
                    anchor = 15.0 to 48.0
                )
                marker = map.addMarker(
                    markerOptions
                )

                val ctx = WeakReference(this)
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
