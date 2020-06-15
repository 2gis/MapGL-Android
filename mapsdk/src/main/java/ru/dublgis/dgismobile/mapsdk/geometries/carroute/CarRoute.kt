package ru.dublgis.dgismobile.mapsdk.geometries.carroute

typealias CarRouteClickCallback = () -> Unit

interface CarRoute {
    /**
     * Destroys the route
     */
    fun destroy()
    /**
     * On click event.
     */
    fun setOnClickListener(listener: CarRouteClickCallback?)
}