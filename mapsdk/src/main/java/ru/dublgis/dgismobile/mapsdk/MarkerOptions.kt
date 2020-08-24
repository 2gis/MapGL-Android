package ru.dublgis.dgismobile.mapsdk

import ru.dublgis.dgismobile.mapsdk.image.Image
import ru.dublgis.dgismobile.mapsdk.labels.LabelOptions


typealias MarkerAnchor = Pair<Double, Double>
typealias MarkerSize = Pair<Double, Double>
@Deprecated("Use Image directly")
typealias MarkerIconDescriptor = Image

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
    val icon: Image? = null,
    /**
     * The position in pixels of the "tip" of the icon (relative to its top left corner).
     */
    val anchor: MarkerAnchor? = null,
    /**
     * Marker icon size [width, height] in pixels.
     */
    val size: MarkerSize? = null,
    /**
     * Initialization options of the marker's label.
     */
    val label: LabelOptions? = null
) {
    override fun toString(): String {
        val builder = StringBuilder()
        builder.append("{")

        builder.append("coordinates: [${position.lon}, ${position.lat}],")
        val imageJsFormat = getImageJsFormat()
        if (imageJsFormat != null) builder.append(" icon: '${imageJsFormat}',")
        if (size != null) builder.append(" size: [${size.first}, ${size.second}],")
        if (anchor != null) builder.append(" anchor: [${anchor.first}, ${anchor.second}],")
        if (label != null) builder.append(" label: $label,")

        builder.append("}")

        return builder.toString()
    }

    private fun getImageJsFormat(): String? {
        icon?.let { return it.toJsFormat() }

        return null
    }
}


