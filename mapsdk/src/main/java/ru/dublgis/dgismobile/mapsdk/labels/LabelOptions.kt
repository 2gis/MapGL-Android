package ru.dublgis.dgismobile.mapsdk.labels

import ru.dublgis.dgismobile.mapsdk.LonLat

/**
 * Label initialization options.
 */
class LabelOptions(
    /**
     * The position in pixels of the "tip" of the label relative to its center.
     */
    var anchor: Float? = null,
    /**
     * Text color in hexadecimal RGB (#ff0000) or RGBA (#ff0000ff) format.
     */
    var color: Int? = null,
    /**
     * Geographical coordinates of label center [longitude, latitude].
     */
    var coordinates: LonLat? = null,
    /**
     * Text size.
     */
    var fontSize: Float? = null,

    /**
     * Color of letters background (when haloRadius is specified). Same format as for color.
     */
    var haloColor: Int? = null,

    /**
     * Use haloRadius to add background behind each letter.
     */
    var haloRadius: Float? = null,
    /**
     * Space between each letter.
     */
    var letterSpacing: Float? = null,

    /**
     * For multiline label lineHeight specify how far lines between each other.
     */
    var lineHeight: Float? = null,

    /**
     * Maximum display zoom level of the label.
     */
    var maxZoom: Float? = null,

    /**
     * Minimum display zoom level of the label.
     */
    var minZoom: Float? = null,

    /**
     * Label's text.
     */
    var text: String,

    /**
     * Draw order.
     */
    var zIndex: Float? = null
) {

    override fun toString(): String {
        val builder = StringBuilder()
        builder.append("{")

        builder.append("text: '$text',")
        if (coordinates != null) builder.append("coordinates: [${coordinates?.lon}, ${coordinates?.lat}],")
        if (color != null) builder.append("color: '${hexColorFormat(color!!)}',")
        if (fontSize != null) builder.append("fontSize: '$fontSize',")
        if (anchor != null) builder.append("anchor: '$anchor',")
        if (haloRadius != null) builder.append("haloRadius: '$haloRadius',")
        if (haloColor != null) builder.append("haloColor: '${hexColorFormat(haloColor!!)}',")
        if (letterSpacing != null) builder.append("letterSpacing: '$letterSpacing',")
        if (lineHeight != null) builder.append("lineHeight: '$lineHeight',")
        if (maxZoom != null) builder.append("maxZoom: '$maxZoom',")
        if (minZoom != null) builder.append("minZoom: '$minZoom',")
        if (zIndex != null) builder.append("zIndex: '$zIndex',")

        builder.append("}")

        return builder.toString()
    }

    private fun hexColorFormat(color: Int): String = String.format(
        "#%06X",
        0xFFFFFF and color
    )
}