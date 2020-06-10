package ru.dublgis.dgismobile.sdktestapp

import org.jetbrains.annotations.TestOnly
import ru.dublgis.dgismobile.mapsdk.LonLat
import ru.dublgis.dgismobile.mapsdk.geometries.PolylineOptions

class GeometriesActivity : MapActivity() {
    override fun onDGisMapReady() {
        showPolyline()
    }

    private fun showPolyline() {
        val polyline = map?.createPolyline(PolylineOptions(generateCoordinates()))
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
