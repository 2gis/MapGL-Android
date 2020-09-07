package ru.dublgis.dgismobile.mapsdk.directions

import ru.dublgis.dgismobile.mapsdk.OnFailure

/**
 * Interface for creating directions on the map.
 */
interface Directions {
    /**
     * Show car route with OnFailure callback.
     */
    fun carRoute(
        carRouteOptions: CarRouteOptions,
        onFailure: OnFailure? = null
    )

    /**
     * Clears the map from any previously drawn routes.
     */
    fun clear()

    /**
     * Destroys the directions
     */
    fun destroy()
}