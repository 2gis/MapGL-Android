package ru.dublgis.dgismobile.mapsdk.directions

/**
 * Interface for creating directions on the map.
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

    /**
     * Destroys the directions
     */
    fun destroy()
}