package ru.dublgis.dgismobile.mapsdk.geometries.circle

typealias CircleClickCallback = () -> Unit

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