package ru.dublgis.dgismobile.mapsdk.directions

import ru.dublgis.dgismobile.mapsdk.OnFailure

/**
 * Interface for creating directions on the map.
 */
interface Directions {

    /**
     * Show car route.
     */
    fun carRoute(carRouteOptions: CarRouteOptions)

    /**
     * Show car route and check the result.
     */
    fun carRoute(
        carRouteOptions: CarRouteOptions,
        onFailure: OnFailure<String>
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