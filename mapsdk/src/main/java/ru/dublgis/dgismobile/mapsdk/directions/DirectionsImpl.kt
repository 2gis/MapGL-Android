package ru.dublgis.dgismobile.mapsdk.directions

import ru.dublgis.dgismobile.mapsdk.OnFinished
import ru.dublgis.dgismobile.mapsdk.PlatformBridge
import java.lang.ref.WeakReference

internal class DirectionsImpl(
    private val controller: WeakReference<PlatformBridge>,
    private val id: String
) : Directions {

    override fun carRoute(
        carRouteOptions: CarRouteOptions,
        onFinished: OnFinished<Unit>?
    ) {
        controller.get()?.carRoute(id, carRouteOptions, onFinished)
    }

    override fun clear() {
        controller.get()?.clearRoutes(id)
    }

    override fun destroy() {
        controller.get()?.destroyDirections(id)
    }
}