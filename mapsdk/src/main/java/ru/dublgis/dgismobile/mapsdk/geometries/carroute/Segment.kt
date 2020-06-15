package ru.dublgis.dgismobile.mapsdk.geometries.carroute

import ru.dublgis.dgismobile.mapsdk.LonLat

/**
 *  The data for the route
 */
class Segment(
    /**
     * Segment color
     */
    var color: String,
    /**
     * Label of the first and last marker
     */
    var label: String = "",
    /**
     *  Array of the points
     */
    var coords: Collection<LonLat>
) {
    override fun toString(): String {
        val builder = StringBuilder()
        builder.append("{color: ${color},")
        if (label.isNotEmpty()) builder.append("label: ${label}, ")
        builder.append("coords: " + coords.joinToString(
            separator = ",",
            prefix = "[",
            postfix = ",]",
            transform = {
                "[${it.lon}, ${it.lat}]"
            }
        ))
        builder.append("}")

        return builder.toString()
    }
}