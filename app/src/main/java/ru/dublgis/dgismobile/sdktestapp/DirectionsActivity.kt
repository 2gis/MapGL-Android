package ru.dublgis.dgismobile.sdktestapp

import android.util.Log
import ru.dublgis.dgismobile.mapsdk.LonLat
import ru.dublgis.dgismobile.mapsdk.MapPointerEvent
import ru.dublgis.dgismobile.mapsdk.directions.CarRouteOptions
import ru.dublgis.dgismobile.mapsdk.directions.Directions
import ru.dublgis.dgismobile.mapsdk.directions.DirectionsOptions

class DirectionsActivity : MapActivity() {

    private var directions1: Directions? = null
    private var directions2: Directions? = null

    override fun onDGisMapReady() {
        carRoute()
        map?.setOnClickListener(this::onMapClicked)
    }

    private fun carRoute() {
        val apiKey = resources.getString(R.string.directions_api_key)

        //first directions
        directions1 = map?.createDirections(DirectionsOptions(apiKey))
        directions1?.carRoute(CarRouteOptions(generate1Coordinates()))

        directions2 = map?.createDirections(DirectionsOptions(apiKey))
        directions2?.carRoute(CarRouteOptions(generate2Coordinates())) {
            it.message?.let { message ->
                Log.d(ru.dublgis.dgismobile.mapsdk.TAG, message)
            }
        }
    }

    private fun onMapClicked(pointer: MapPointerEvent) {
        directions1?.clear()
        directions2?.destroy()
    }

    private fun generate1Coordinates(): Collection<LonLat> {
        val coordinates = mutableListOf<LonLat>()
        coordinates.add(LonLat(55.28770929, 25.22069944))
        coordinates.add(LonLat(55.28976922, 25.25656786))
        coordinates.add(LonLat(55.33096795, 25.22007825))
        coordinates.add(LonLat(55.33302789, 25.25687836))
        coordinates.add(LonLat(55.30730, 25.20113))

        return coordinates
    }

    private fun generate2Coordinates(): Collection<LonLat> {
        val coordinates = mutableListOf<LonLat>()
        coordinates.add(LonLat(55.26770929, 25.20069944))
        //coordinates.add(LonLat(55.32730, 25.12113))

        return coordinates
    }
}
