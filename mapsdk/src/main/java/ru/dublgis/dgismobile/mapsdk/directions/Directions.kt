package ru.dublgis.dgismobile.mapsdk.directions

import androidx.annotation.RequiresApi
import ru.dublgis.dgismobile.mapsdk.OnFinished
import java.util.concurrent.CompletableFuture


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
     * Show car route.
     * The method is available only for Android API 24 and above.
     *
     * @param carRouteOptions - Car route options.
     * @return CompletableFuture with result or Exception
     */
    @RequiresApi(24)
    fun carRoute(
        carRouteOptions: CarRouteOptions
    ) : CompletableFuture<Unit>

    /**
     * Show pedestrian route.
     * @param options - Pedestrian route options.
     * @param onFinished - Callback with the result: exception if error, else Unit.
     */
   fun pedestrianRoute(
        options: PedestrianRouteOptions,
        onFinished: OnFinished<Unit>? = null
    )

    /**
     * Show pedestrian route.
     * The method is available only for Android API 24 and above.
     *
     * @param options - Pedestrian route options.
     * @return CompletableFuture with result or Exception
     */
    @RequiresApi(24)
    fun pedestrianRoute(
        options: PedestrianRouteOptions
    ) : CompletableFuture<Unit>

    /**
     * Clears the map from any previously drawn routes.
     */
    fun clear()

    /**
     * Destroys the directions
     */
    fun destroy()
}