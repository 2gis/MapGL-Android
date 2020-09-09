package ru.dublgis.dgismobile.mapsdk.directions

import ru.dublgis.dgismobile.mapsdk.OnFinished


/**
 * Interface for creating directions on the map.
 */
interface Directions {
    /**
     * Show car route.
     * @param carRouteOptions - Car route options.
     * @param onFinished - Callback with the result: exception if error, else Unit.
     */
    fun carRoute(
        carRouteOptions: CarRouteOptions,
        onFinished: OnFinished<Unit>? = null
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