package ru.dublgis.dgismobile.mapsdk.geometries.polygon

typealias PolygonClickCallback = () -> Unit

interface Polygon {

    /**
     * Destroys the polygon.
     */
    fun destroy()

    /**
     * On click event.
     */
    fun setOnClickListener(listener: PolygonClickCallback?)
}