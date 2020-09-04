package ru.dublgis.dgismobile.mapsdk.directions

import ru.dublgis.dgismobile.mapsdk.OnFailure
import ru.dublgis.dgismobile.mapsdk.PlatformBridge
import java.lang.ref.WeakReference

internal class DirectionsImpl(
    private val controller: WeakReference<PlatformBridge>,
    private val id: String
) : Directions {
    override fun carRoute(carRouteOptions: CarRouteOptions) {
        controller.get()?.carRoute(id, carRouteOptions)
    }

    override fun carRoute(
        carRouteOptions: CarRouteOptions,
        onFailure: OnFailure
    ) {
        controller.get()?.carRoute(id, carRouteOptions, onFailure)
    }

    override fun clear() {
        controller.get()?.clearRoutes(id)
    }

    override fun destroy() {
        controller.get()?.destroyDirections(id)
    }
}