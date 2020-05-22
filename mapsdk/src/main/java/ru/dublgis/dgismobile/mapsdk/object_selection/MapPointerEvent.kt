package ru.dublgis.dgismobile.mapsdk.object_selection

import ru.dublgis.dgismobile.mapsdk.LonLat

data class MapPointerEvent(
    val lngLat: LonLat,
    val target: EventTarget
)