package ru.dublgis.dgismobile.sdktestapp

import android.util.Log
import ru.dublgis.dgismobile.mapsdk.*
import ru.dublgis.dgismobile.mapsdk.directions.Directions
import ru.dublgis.dgismobile.mapsdk.directions.DirectionsOptions
import ru.dublgis.dgismobile.mapsdk.directions.PedestrianRouteOptions
import ru.dublgis.dgismobile.mapsdk.image.ImageFactory

class PedestrianRouteActivity : MapActivity() {

    private var directions: Directions? = null
    private var markers = arrayOfNulls<Marker>(2)

    override fun onDGisMapReady() {
        val map = this.map ?: return
        map.setOnClickListener(this::onMapClicked)
    }

    private fun onRouteComplete(result: Result<Unit>) {
        markers.forEach {
            it?.hide()
        }
    }

    private fun addMarker(idx: Int, point: MapPointerEvent) {
        val map = checkNotNull(this.map)

        val options = MarkerOptions(
            position = point.lngLat,
            anchor = MarkerAnchor(12.0, 24.0),
            size = MarkerSize(24.0, 24.0),
            icon = ImageFactory(applicationContext).fromResource(R.drawable.ic_red_nav_pin)
        )
        markers[idx] = map.addMarker(options)
    }

    private fun onMapClicked(point: MapPointerEvent) {
        val map = checkNotNull(this.map)

        when {
            markers[0] == null -> {
                addMarker(0, point)
            }
            markers[1] == null -> {
                addMarker(1, point)

                val points = markers.map {
                    it!!.position
                }
                directions = map.createDirections(
                    DirectionsOptions(resources.getString(R.string.directions_api_key))
                ).also {
                    it.pedestrianRoute(
                        PedestrianRouteOptions(points = points.toList()),
                        this::onRouteComplete
                    )
                }
            }
            else -> {
                markers.forEach {
                    it?.let(map::removeMarker)
                }
                markers = arrayOfNulls<Marker>(2)

                directions?.destroy()
                directions = null
            }
        }
    }
}