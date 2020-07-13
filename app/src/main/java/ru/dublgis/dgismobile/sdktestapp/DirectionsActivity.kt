package ru.dublgis.dgismobile.sdktestapp

import org.jetbrains.annotations.TestOnly
import ru.dublgis.dgismobile.mapsdk.LonLat
import ru.dublgis.dgismobile.mapsdk.directions.CarRouteOptions
import ru.dublgis.dgismobile.mapsdk.directions.DirectionsOptions

class DirectionsActivity : MapActivity() {
    override fun onDGisMapReady() {
        carRoute()
    }

    private fun carRoute() {
        val apiKey = resources.getString(R.string.dgis_map_key)

        val directions = map?.createDirections(DirectionsOptions(apiKey))
        directions?.carRoute(CarRouteOptions(generateCoordinates()))
    }

    @TestOnly
    private fun generateCoordinates(): Collection<LonLat> {
        val coordinates = mutableListOf<LonLat>()
        coordinates.add(LonLat(55.28770929, 25.22069944))
        coordinates.add(LonLat(55.28976922, 25.25656786))
        coordinates.add(LonLat(55.33096795, 25.22007825))
        coordinates.add(LonLat(55.33302789, 25.25687836))
        coordinates.add(LonLat(55.30730, 25.20113))

        return coordinates
    }
}
