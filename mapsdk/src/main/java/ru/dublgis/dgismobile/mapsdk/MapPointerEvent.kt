package ru.dublgis.dgismobile.mapsdk


data class MapPointerEvent(
    val lngLat: LonLat,
    val target: MapObject?
)