package ru.dublgis.dgismobile.mapsdk.labels

import ru.dublgis.dgismobile.mapsdk.BaseOptions
import ru.dublgis.dgismobile.mapsdk.LonLat

typealias LabelAnchor = Pair<Double, Double>

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
    val zIndex: Float? = null
) : BaseOptions() {

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

        builder.append("}")

        return builder.toString()
    }
}