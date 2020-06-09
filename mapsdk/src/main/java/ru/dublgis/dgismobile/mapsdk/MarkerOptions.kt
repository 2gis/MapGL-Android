package ru.dublgis.dgismobile.mapsdk


typealias MarkerAnchor = Pair<Double, Double>
typealias MarkerSize = Pair<Double, Double>

/**
 * Marker initialization options.
 */
class MarkerOptions(
    /**
     * Geographical coordinates of marker center [longitude, latitude].
     */
    var position: LonLat,
    /**
     * Marker icon URL.
     */
    val icon: MarkerIconDescriptor? = null,
    /**
     * The position in pixels of the "tip" of the icon (relative to its top left corner).
     */
    val anchor: MarkerAnchor? = null,
    /**
     * Marker icon size [width, height] in pixels.
     */
    val size: MarkerSize? = null
)
