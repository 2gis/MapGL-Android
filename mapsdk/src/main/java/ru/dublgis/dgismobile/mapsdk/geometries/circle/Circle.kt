package ru.dublgis.dgismobile.mapsdk.geometries.circle

typealias CircleClickCallback = () -> Unit

/**
 * Class for creating a circle on the map.
 */
interface Circle {

    /**
     * Destroys the circle.
     */
    fun destroy()
    /**
     * On click event.
     */
    fun setOnClickListener(listener: CircleClickCallback?)
}