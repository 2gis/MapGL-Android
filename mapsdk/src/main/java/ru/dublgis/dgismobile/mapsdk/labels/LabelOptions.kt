package ru.dublgis.dgismobile.mapsdk.labels

import ru.dublgis.dgismobile.mapsdk.LonLat
import ru.dublgis.dgismobile.mapsdk.utils.ColorUtils.jsColorFormat

typealias LabelAnchor = Pair<Double, Double>
typealias LabelNumber = Pair<Double, Double>

/**
 * Label initialization options.
 */
class LabelOptions(
    /**
     * The position in pixels of the "tip" of the label relative to its center.
     */
    val anchor: LabelAnchor? = null,
    /**
     * Text color in hexadecimal RGB (#ff0000) or RGBA (#ff0000ff) format.
     */
    val color: Int? = null,
    /**
     * Geographical coordinates of label center [longitude, latitude].
     */
    val coordinates: LonLat? = null,
    /**
     * Text size.
     */
    val fontSize: Float? = null,

    /**
     * Color of letters background (when haloRadius is specified). Same format as for color.
     */
    val haloColor: Int? = null,

    /**
     * Use haloRadius to add background behind each letter.
     */
    val haloRadius: Float? = null,
    /**
     * Space between each letter.
     */
    val letterSpacing: Float? = null,

    /**
     * For multiline label lineHeight specify how far lines between each other.
     */
    val lineHeight: Float? = null,

    /**
     * Maximum display zoom level of the label.
     */
    val maxZoom: Float? = null,

    /**
     * Minimum display zoom level of the label.
     */
    val minZoom: Float? = null,

    /**
     * Label's text.
     */
    val text: String,

    /**
     * Draw order.
     */
    val zIndex: Float? = null,
    /**
     * Image background for the label.
     */
    val image: LabelImage? = null,
    /**
     * The offset distance of text box from its relativeAnchor.
     * Positive values indicate right and down, while negative values indicate left and up.
     */
    val offset: LabelNumber? = null,
    /**
     * The relative, from 0 to 1 in each dimension, coordinates of the text box "tip".
     * relative to its top left corner, for example: [0, 0] value is the top left corner,
     * [0.5, 0.5] — center point, and [1, 1] is the bottom right corner of the box.
     * The label will be placed so that this point is at geographical coordinates
     * respects the absolute offset.
     */
    val relativeAnchor: LabelNumber? = null
) {

    override fun toString(): String {
        val builder = StringBuilder()
        builder.append("{")

        builder.append("text: \"$text\",")
        if (coordinates != null) builder.append("coordinates: [${coordinates.lon}, ${coordinates.lat}],")
        if (color != null) builder.append("color: '${jsColorFormat(color)}',")
        if (fontSize != null) builder.append("fontSize: $fontSize,")
        if (anchor != null) builder.append(" anchor: [${anchor.first}, ${anchor.second}],")
        if (haloRadius != null) builder.append("haloRadius: $haloRadius,")
        if (haloColor != null) builder.append("haloColor: '${jsColorFormat(haloColor)}',")
        if (letterSpacing != null) builder.append("letterSpacing: $letterSpacing,")
        if (lineHeight != null) builder.append("lineHeight: '$lineHeight',")
        if (maxZoom != null) builder.append("maxZoom: $maxZoom,")
        if (minZoom != null) builder.append("minZoom: $minZoom,")
        if (zIndex != null) builder.append("zIndex: $zIndex,")
        if (image != null) builder.append("image: $image,")
        if (offset != null) builder.append(" offset: [${offset.first}, ${offset.second}],")
        if (relativeAnchor != null) builder.append(" relativeAnchor: [${relativeAnchor.first}, ${relativeAnchor.second}],")

        builder.append("}")

        return builder.toString()
    }
}