package ru.dublgis.dgismobile.mapsdk.geometries.carroute

import ru.dublgis.dgismobile.mapsdk.PlatformBridge
import java.lang.ref.WeakReference

internal class CarRouteImpl(
    private val controller: WeakReference<PlatformBridge>
) : CarRoute {

    var onClick: CarRouteClickCallback? = null

    override fun destroy() {
        controller.get()?.destroyCarRoute()
    }

    override fun setOnClickListener(listener: CarRouteClickCallback?) {
        onClick = listener
    }
}