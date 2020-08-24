package ru.dublgis.dgismobile.mapsdk.image

/**
 * Image is used to display bitmaps on top of the map using Marker.
 */
interface Image {
    fun toJsFormat(): String
}