package ru.dublgis.dgismobile.mapsdk.utils.icon

/**
 * Icon is used to display bitmaps on top of the map using Marker.
 */
interface Icon {
    fun toJsFormat(): String
}