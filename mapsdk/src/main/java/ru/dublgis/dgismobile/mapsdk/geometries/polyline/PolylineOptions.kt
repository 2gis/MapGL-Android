package ru.dublgis.dgismobile.mapsdk.geometries.polyline

import ru.dublgis.dgismobile.mapsdk.LonLat

/**
 * Polyline initialization options.
 */
class PolylineOptions(
    /**
     * An array of polyline coordinates: [firstPoint, secondPoint, ...]. Each point is a geographical point: [longitude, latitude].
     */
    var coordinates: Collection<LonLat>
)