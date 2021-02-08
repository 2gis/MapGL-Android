package ru.dublgis.dgismobile.sdktestapp

import android.widget.Toast
import ru.dublgis.dgismobile.mapsdk.LonLat
import ru.dublgis.dgismobile.mapsdk.MapPointerEvent
import ru.dublgis.dgismobile.mapsdk.Marker
import ru.dublgis.dgismobile.mapsdk.MarkerOptions
import ru.dublgis.dgismobile.mapsdk.image.ImageFactory
import ru.dublgis.dgismobile.mapsdk.labels.LabelImage
import ru.dublgis.dgismobile.mapsdk.labels.LabelOptions
import java.lang.ref.WeakReference
import java.net.URL


class MarkersActivity : MapActivity() {

    private var marker: Marker? = null

    override fun onDGisMapReady() {
        map?.setOnClickListener(this::onMapClicked)
        showMarkerLabel()
        showMarker()
        showMarkerWithCustomIcon()
        showMarkerWithLabelBackground()
        showMarkerFromUrl()
    }

    private fun showMarkerWithLabelBackground() {
        map?.addMarker(
            MarkerOptions(
                LonLat(55.2803513, 25.1593204),
                label = LabelOptions(
                    text = "The marker's label",
                    offset = 0.0 to 25.0,
                    relativeAnchor = 0.5 to 0.0,
                    image = LabelImage(
                        image = ImageFactory(this).fromAsset("tooltip-top.svg"),
                        size = 100 to 50,
                        stretchX = listOf(10 to 40, 60 to 90),
                        stretchY = listOf(20 to 40),
                        padding = listOf(20, 10, 10, 10)
                    )
                )
            )
        )
    }

    private fun showMarkerWithCustomIcon() {
        map?.addMarker(
            MarkerOptions(
                LonLat(55.2762656, 25.2228653),
                icon = ImageFactory(this).fromAsset("marker.svg")
            )
        )
    }

    private fun showMarkerFromUrl() {
        map?.addMarker(
            MarkerOptions(
                LonLat(lon=55.32288096, lat=25.1720255),
                icon = ImageFactory(this).fromUrl(URL("https://docs.2gis.com/img/mapgl/marker.svg"))
            )
        )
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
                val markerOptions = MarkerOptions(
                    pointer.lngLat,
                    icon = ImageFactory(this).fromResource(R.drawable.splash_logo),
                    size = 60.0 to 20.0,
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
