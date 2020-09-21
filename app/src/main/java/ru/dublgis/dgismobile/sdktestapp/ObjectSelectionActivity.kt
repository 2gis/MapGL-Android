package ru.dublgis.dgismobile.sdktestapp

import ru.dublgis.dgismobile.mapsdk.LonLat
import ru.dublgis.dgismobile.mapsdk.MapPointerEvent

class ObjectSelectionActivity : MapActivity() {

    override fun onDGisMapReady() {
        zoomMap(18.0)
        centerMap(LonLat(55.308, 25.237))
        map?.setOnClickListener(this::onMapClicked)
    }

    private fun onMapClicked(pointer: MapPointerEvent) {
        map?.let { map ->
            pointer.target?.let { mapObject ->
                map.setSelectedObjects(listOf(mapObject))
            }
        }
    }
}
