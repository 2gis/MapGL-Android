package ru.dublgis.dgismobile.mapsdk

import android.util.JsonWriter

/**
 * Padding in density independent pixels from the different sides of the map canvas.
 */
data class Padding(
    val bottom: Float = 0.0f,
    val left: Float = 0.0f,
    val right: Float = 0.0f,
    val top: Float = 0.0f
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

    companion object {
        val EMPTY = Padding()
    }
}
