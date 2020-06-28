package ru.dublgis.dgismobile.mapsdk.geometries.polygon

import ru.dublgis.dgismobile.mapsdk.LonLat
import ru.dublgis.dgismobile.mapsdk.utils.ColorUtils
import ru.dublgis.dgismobile.mapsdk.utils.ColorUtils.jsColorFormat

/**
 * Polygon initialization options.
 */
class PolygonOptions(
    /**
     * Geographical coordinates of polygon points in format: [outerEdges, cropEdges1, cropEdges2, ...].
     * The first section is outerEdges which describes an array of outer edges: [firstPoint,
     * secondPoint, ..., firstPoint]. Each point is a geographical point: [longitude, latitude].
     * The last point should be the same as the first.
     */
    var coordinates: Collection<Collection<LonLat>>,

    /**
     * Fill color in hexadecimal RGB (#ff0000) or RGBA (#ff0000ff) format.
     */
    var color: Int,

    /**
     * Stroke width in pixels.
     */
    var strokeWidth: Float,
    /**
     * Stroke color in hexadecimal RGB (#ff0000) or RGBA (#ff0000ff) format.
     */
    var strokeColor: Int
) {
    override fun toString(): String {
        val builder = StringBuilder()
        builder.append("{")

        val coords = coordinates.joinToString(
            separator = ",",
            prefix = "[",
            postfix = ",]",
            transform = { it ->
                it.joinToString(
                    separator = ",",
                    prefix = "[",
                    postfix = ",]",
                    transform = {
                        "[${it.lon}, ${it.lat}]"
                    }
                )
            }
        )
        builder.append("coordinates: $coords, ")
        builder.append("color: '${jsColorFormat(color)}', ")
        builder.append("strokeWidth: $strokeWidth, ")
        builder.append("strokeColor: '${jsColorFormat(strokeColor)}',")

        builder.append("}")

        return builder.toString()
    }
}