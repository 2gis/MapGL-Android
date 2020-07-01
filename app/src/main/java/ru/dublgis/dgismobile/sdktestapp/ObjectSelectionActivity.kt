package ru.dublgis.dgismobile.sdktestapp

import ru.dublgis.dgismobile.mapsdk.MapPointerEvent

class ObjectSelectionActivity : MapActivity() {

    override fun onDGisMapReady() {
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
