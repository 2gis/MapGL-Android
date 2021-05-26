package ru.dublgis.dgismobile.mapsdk

import android.util.JsonWriter

/**
 * Options for Map.fitBounds.
 */
data class FitBoundsOptions(
    /**
     * If true the fitBounds will consider the map rotation.
     */
    val considerRotation: Boolean = false,
    /**
     * The amount of padding in pixels to add to the given bounds.
     */
    val padding: Padding? = null
) : PlatformSerializable {
    override fun dump(writer: JsonWriter) {
        writer.apply {
            beginObject()
            name("considerRotation").value(considerRotation)
            name("padding").value(padding)
            endObject()
        }
    }
}
