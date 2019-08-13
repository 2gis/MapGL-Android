package ru.dublgis.dgismobile.mapsdk


typealias MarkerAnchor = Pair<Double, Double>
typealias MarkerSize = Pair<Double, Double>


class MarkerOptions(
    var position: LonLat,
    val icon: MarkerIconDescriptor? = null,
    val anchor: MarkerAnchor? = null,
    val size: MarkerSize? = null
)
