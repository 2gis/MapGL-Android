package ru.dublgis.dgismobile.mapsdk.geometries.circle.circlemarker

import ru.dublgis.dgismobile.mapsdk.PlatformBridge
import ru.dublgis.dgismobile.mapsdk.geometries.circle.CircleImpl
import java.lang.ref.WeakReference

internal class CircleMarkerImpl(
    private val controller: WeakReference<PlatformBridge>,
    private val id: String
) : CircleMarker, CircleImpl(controller, id) {

    override fun destroy() {
        controller.get()?.destroyCircleMarker(id)
    }
}