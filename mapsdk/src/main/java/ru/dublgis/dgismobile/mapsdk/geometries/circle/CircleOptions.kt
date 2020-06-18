package ru.dublgis.dgismobile.mapsdk.geometries.circle

import ru.dublgis.dgismobile.mapsdk.LonLat

/**
 * Circle initialization options.
 */
class CircleOptions(
    /**
     * Geographical coordinates of the circle center: [longitude, latitude].
     */
    var coordinates: LonLat,
    /**
     * Circle radius in meters.
     */
    var radius: Float
)