package ru.dublgis.dgismobile.mapsdk.directions

import ru.dublgis.dgismobile.mapsdk.OnFinished
import ru.dublgis.dgismobile.mapsdk.PlatformBridge
import java.lang.IllegalStateException
import java.lang.ref.WeakReference

internal class DirectionsImpl(
    private val controller: WeakReference<PlatformBridge>,
    private val id: String
) : Directions {

    private val bridge: PlatformBridge
        get() {
            return controller.get() ?: throw IllegalStateException("Web Bridge is not available")
        }

    override fun carRoute(
        carRouteOptions: CarRouteOptions,
        onFinished: OnFinished<Unit>?
    ) = bridge.carRoute(id, carRouteOptions, onFinished)

    override fun pedestrianRoute(
        options: PedestrianRouteOptions,
        onFinished: OnFinished<Unit>?
    ) = bridge.pedestrianRoute(id, options, onFinished)

    override fun clear() = bridge.clearRoutes(id)

    override fun destroy() = bridge.destroyDirections(id)
}