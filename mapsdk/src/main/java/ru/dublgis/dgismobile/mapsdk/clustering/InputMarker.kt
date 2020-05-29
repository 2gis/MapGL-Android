package ru.dublgis.dgismobile.mapsdk.clustering

import ru.dublgis.dgismobile.mapsdk.LonLat
import ru.dublgis.dgismobile.mapsdk.MarkerAnchor
import ru.dublgis.dgismobile.mapsdk.MarkerIconDescriptor
import ru.dublgis.dgismobile.mapsdk.MarkerSize

class InputMarker(
    var position: LonLat,
    val icon: MarkerIconDescriptor? = null,
    val anchor: MarkerAnchor? = null,
    val size: MarkerSize? = null
)