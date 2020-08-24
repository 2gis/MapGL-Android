package ru.dublgis.dgismobile.mapsdk

import ru.dublgis.dgismobile.mapsdk.labels.LabelOptions
import ru.dublgis.dgismobile.mapsdk.utils.image.Image


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

    private var markerIconDescriptor: MarkerIconDescriptor? = null

    @Deprecated("This constructor is deprecated. Use primary constructor instead.")
    constructor(
        /**
         * Geographical coordinates of marker center [longitude, latitude].
         */
        position: LonLat,
        /**
         * Marker icon URL.
         */
        icon: MarkerIconDescriptor,
        /**
         * The position in pixels of the "tip" of the icon (relative to its top left corner).
         */
        anchor: MarkerAnchor? = null,
        /**
         * Marker icon size [width, height] in pixels.
         */
        size: MarkerSize? = null,
        /**
         * Initialization options of the marker's label.
         */
        label: LabelOptions? = null
    ) : this(position = position, icon = null, anchor = anchor, size = size, label = label) {
        markerIconDescriptor = icon
    }


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
        markerIconDescriptor?.let { return (it as MarkerIconDescriptorImpl).toJsFormat() }

        return null
    }
}


