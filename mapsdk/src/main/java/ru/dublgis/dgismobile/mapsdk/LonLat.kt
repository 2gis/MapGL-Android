package ru.dublgis.dgismobile.mapsdk

import kotlin.math.abs


/**
 * Geographical coordinates.
 */
data class LonLat(
    /**
     * Longitude
     */
    val lon: Double = 0.0,
    /**
     * Latitude
     */
    val lat: Double = 0.0

) {
    /**
     *  Used to compare equality of two LonLat objects.
     *
     *  @param other object to compare.
     */
    override fun equals(other: Any?): Boolean {
        if (this === other)
            return true

        if (other?.javaClass != javaClass)
            return false

        other as LonLat

        val closeEnough = { x: Double, y: Double ->
            abs(x - y) < 0.000001
        }

        return closeEnough(lon, other.lon) && closeEnough(lat, other.lat)
    }
}