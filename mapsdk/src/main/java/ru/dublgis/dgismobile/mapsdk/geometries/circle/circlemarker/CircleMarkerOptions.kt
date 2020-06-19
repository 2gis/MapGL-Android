package ru.dublgis.dgismobile.mapsdk.geometries.circle.circlemarker

import ru.dublgis.dgismobile.mapsdk.LonLat

/**
 * CircleMarker initialization options.
 */
class CircleMarkerOptions(
    /**
     * Geographical coordinates of the circleMarker center: [longitude, latitude].
     */
    var coordinates: LonLat,
    /**
     * Circle radius in pixels.
     */
    var radius: Float
)