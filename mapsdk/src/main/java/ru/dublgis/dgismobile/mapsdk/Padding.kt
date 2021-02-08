package ru.dublgis.dgismobile.mapsdk

import android.util.JsonWriter

/**
 * Padding in pixels from the different sides of the map canvas.
 */
data class Padding(
    val bottom: Int = 0,
    val left: Int = 0,
    val right: Int = 0,
    val top: Int = 0
) : PlatformSerializable {
    override fun dump(writer: JsonWriter) {
        writer.apply {
            beginObject()
            name("bottom").value(bottom)
            name("left").value(left)
            name("right").value(right)
            name("top").value(top)
            endObject()
        }
    }
}
