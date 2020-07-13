package ru.dublgis.dgismobile.mapsdk.directions

import ru.dublgis.dgismobile.mapsdk.PlatformBridge
import java.lang.ref.WeakReference

internal class DirectionsImpl(
    private val controller: WeakReference<PlatformBridge>
) : Directions {
    override fun carRoute(carRouteOptions: CarRouteOptions) {
        controller.get()?.carRoute(carRouteOptions)
    }

    override fun clear() {
        controller.get()?.clearRoutes()
    }
}