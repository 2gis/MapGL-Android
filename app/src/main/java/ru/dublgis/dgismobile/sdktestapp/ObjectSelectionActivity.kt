package ru.dublgis.dgismobile.sdktestapp

import ru.dublgis.dgismobile.mapsdk.LonLat
import ru.dublgis.dgismobile.mapsdk.MapPointerEvent

class ObjectSelectionActivity : MapActivity() {

    override fun onDGisMapReady() {
        map?.apply {
            center = LonLat(55.308, 25.237)
            zoom = 18.0
            setOnClickListener(ObjectSelectionActivity@ ::onMapClicked)
        }
    }

    private fun onMapClicked(pointer: MapPointerEvent) {
        map?.let { map ->
            pointer.target?.let { mapObject ->
                map.setSelectedObjects(listOf(mapObject))
            }
        }
    }
}
