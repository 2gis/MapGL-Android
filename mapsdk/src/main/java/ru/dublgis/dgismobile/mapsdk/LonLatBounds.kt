package ru.dublgis.dgismobile.mapsdk

import android.util.JsonWriter

/**
 * Geographical bounds.
 */
data class LonLatBounds(
    /**
     * The north-east point of the bounds [longitude, latitude].
     */
    val northEast: LonLat,
    /**
     * The south-west point of the bounds [longitude, latitude].
     */
    val southWest: LonLat
) : PlatformSerializable {
    override fun dump(writer: JsonWriter) {
        writer.apply {
            beginObject()
            name("northEast")
                northEast.dump(writer)
            name("southWest")
                southWest.dump(writer)
            endObject()
        }
    }
}
