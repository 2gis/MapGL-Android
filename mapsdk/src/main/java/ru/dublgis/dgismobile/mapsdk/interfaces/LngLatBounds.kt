package ru.dublgis.dgismobile.mapsdk.interfaces

import ru.dublgis.dgismobile.mapsdk.LonLat

/**
 * Geographical bounds.
 */
class LngLatBounds(
    /**
     * The north-east point of the bounds [longitude, latitude].
     */
    val northEast: LonLat,
    /**
     * The south-west point of the bounds [longitude, latitude].
     */
    val southWest: LonLat
)