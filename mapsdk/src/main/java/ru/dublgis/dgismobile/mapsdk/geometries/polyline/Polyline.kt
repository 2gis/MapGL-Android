package ru.dublgis.dgismobile.mapsdk.geometries.polyline

typealias PolylineClickCallback = () -> Unit

interface Polyline {

    /**
     * Destroys the polyline.
     */
    fun destroy()
    /**
     * On click event.
     */
    fun setOnClickListener(listener: PolylineClickCallback?)
}