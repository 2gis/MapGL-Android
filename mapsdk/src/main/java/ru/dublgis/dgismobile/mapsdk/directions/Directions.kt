package ru.dublgis.dgismobile.mapsdk.directions

/**
 * Interface for creating labels on the map.
 */
interface Directions {

    /**
     * Show car route.
     */
    fun carRoute(carRouteOptions: CarRouteOptions)

    /**
     * Clears the map from any previously drawn routes.
     */
    fun clear()
}